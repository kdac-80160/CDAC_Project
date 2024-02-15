package com.app.service;

import java.time.LocalDateTime;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ApiException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.UserEntityDao;
import com.app.dao.UserOTPDao;
import com.app.dto.ApiResponse;
import com.app.dto.ResetPasswordDTO;
import com.app.dto.Signup;
import com.app.entities.UserEntity;
import com.app.entities.UserOTP;
import com.app.enums.ResponseStatus;
import com.app.utils.EmailUtil;
import com.app.utils.OtpUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	// dep : dao layer i/f
	@Autowired
	private UserEntityDao userDao;
	// dep
	@Autowired
	private ModelMapper mapper;
	// dep
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserOTPDao otpDao;

	@Autowired
	private OtpUtil otpUtil;

	@Autowired
	private EmailUtil emailUtil;

	@Override
	public Signup userRegistration(Signup reqDTO) {
		// dto --> entity
		UserEntity user = mapper.map(reqDTO, UserEntity.class);
		user.setPassword(encoder.encode(user.getPassword()));// pwd : encrypted using SHA
		user.setRegistrationTime(LocalDateTime.now());
		return mapper.map(userDao.save(user), Signup.class);
	}

	@Override
	public ApiResponse otpGeneration(String email) {
		UserEntity user = userDao.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Email does not exist."));
		String otp = otpUtil.generateOTP();
		UserOTP userOtp = otpDao.findByUserEmail(email).orElse(null);
		if(userOtp == null)
		{
			userOtp = new UserOTP(user, otp);
			otpDao.save(userOtp);
		}
		else
		{
			userOtp.setOtp(otp);
		}
		try {
			emailUtil.sendOtpToEmail(email, otp);
			return new ApiResponse("OTP sent to your email.", ResponseStatus.SUCCESS);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new ApiException("OTP generation failed.");
		}
	}

	@Override
	public ApiResponse resetPassword(ResetPasswordDTO resetPassDTO) {
		UserEntity user = userDao.findByEmail(resetPassDTO.getEmail())
				.orElseThrow(()-> new ResourceNotFoundException("Email does not exist"));
		UserOTP otp = otpDao.findByUserEmail(resetPassDTO.getEmail())
				.orElseThrow(()-> new ResourceNotFoundException("There is no otp for the given email."));
		if(otp.getOtp().equals(resetPassDTO.getOtp()))
		{
			user.setPassword(encoder.encode(resetPassDTO.getNewPassword()));
			otpDao.delete(otp);
			return new ApiResponse("Password updated Successfully.",ResponseStatus.SUCCESS);
		}
		else
		{
			return new ApiResponse("Wrong OTP entered.",ResponseStatus.FAILED);
		}
	}

}

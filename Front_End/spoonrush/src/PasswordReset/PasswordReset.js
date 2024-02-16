import React, { useState } from 'react'
import { ToastContainer, toast } from 'react-toastify';

const PasswordReset = () => {
    const [email, setEmail] = useState("");
    const [otp, setOTP] = useState("");
    const [isOTPSent, setIsOTPSent] = useState(false);

    const handleUserInput = (e) => {
        setEmail(e.target.value);
    };

    const handleSendOTP = () => {
        // Logic to send OTP to the user's email address
        // Example: You can make a fetch call to your backend API to send the OTP
        // Example: fetch('/api/send-otp', { method: 'POST', body: { email } })

        // For demonstration, let's assume the OTP is sent successfully
        setIsOTPSent(true);
        toast.success("OTP has been sent to your email.");
    };

    const handleVerifyOTP = () => {
        // Logic to verify the OTP entered by the user
        // Example: You can make a fetch call to your backend API to verify the OTP
        // Example: fetch('/api/verify-otp', { method: 'POST', body: { email, otp } })

        // For demonstration, let's assume the OTP is verified successfully
        toast.success("OTP has been verified successfully. You can reset your password now.");
        // Implement further logic to allow the user to reset their password
    };
  return (
    <div>
            <div className="mt-2 d-flex aligns-items-center justify-content-center">
                <div className="form-card border-color" style={{ width: "37rem" }}>
                    <div className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center" style={{ borderRadius: "0em", height: "38px" }}>
                        <h4 className="card-title">Forgot Password</h4>
                    </div>
                    <div className="card-body mt-3">
                        <form>
                            <div className="mb-3 text-color">
                                <label htmlFor="email" className="form-label">
                                    <b>Email Id</b>
                                </label>
                                <input
                                    type="email"
                                    className="form-control"
                                    id="email"
                                    name="email"
                                    onChange={handleUserInput}
                                    value={email}
                                />
                            </div>
                            {!isOTPSent ? (
                                <div className="d-flex aligns-items-center justify-content-center mb-2">
                                    <button
                                        type="button"
                                        className="btn bg-color custom-bg-text"
                                        onClick={handleSendOTP}
                                    >
                                        Send OTP
                                    </button>
                                </div>
                            ) : (
                                <>
                                    <div className="mb-3 text-color">
                                        <label htmlFor="otp" className="form-label">
                                            <b>Enter OTP</b>
                                        </label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            id="otp"
                                            name="otp"
                                            onChange={(e) => setOTP(e.target.value)}
                                            value={otp}
                                        />
                                    </div>
                                    <div className="d-flex aligns-items-center justify-content-center mb-2">
                                        <button
                                            type="button"
                                            className="btn bg-color custom-bg-text"
                                            onClick={handleVerifyOTP}
                                        >
                                            Verify OTP
                                        </button>
                                    </div>
                                </>
                            )}
                            <ToastContainer />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
 };

export default PasswordReset

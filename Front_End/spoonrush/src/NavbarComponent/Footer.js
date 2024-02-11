import React from "react";
import "../Styles/generic.css";
import { IconName } from "react-icons/fa6";
import { FaInstagram, FaSquareYoutube } from "react-icons/fa6";
import { FaSquareXTwitter } from "react-icons/fa6";
import pic from "../Images/logo.png";

const Footer = () => {
  return (
    <div>
      <footer>
        <div className="container grid-four-column">
          <div className="footer-logo">
            <h3>
              <img src={pic} alt="img" className="icon-img" /> SpoonRush
            </h3>
            <p>© 2023 Some Technologies Pvt. Ltd</p>
          </div>

          <div className="footer-sub footer-logo">
            <h3>Company</h3>
            <ul>
              <li>
                <a href="../PageComponents/AboutUS.js" target="_blank">
                  About
                </a>
              </li>
              <li>
                <a href="../PageComponents/Team.js">Team</a>
              </li>
              <li>
                <a href="../PageComponents/ContactUs.js">Contact</a>
              </li>
            </ul>
          </div>

          <div className="footer-sub footer-logo">
            <h3>Contact Us</h3>
            <ul>
              <li>
                <a href="../PageComponents/ContactUs.js" target="_blank">
                  Help & Support
                </a>
              </li>
              <li>
                <a href="../PageComponents/AboutUS.js">Partner with us</a>
              </li>
            </ul>
          </div>

          <div className="footer-sub footer-logo grid-two-column">
            <h3>Social Media</h3>
            <div className="footer-social--icons">
              <div>
                <FaInstagram className="icons" />
              </div>
              <div>
                <FaSquareYoutube className="icons" />
              </div>
              <div>
                <FaSquareXTwitter className="icons" />
              </div>
            </div>
          </div>
        </div>
        <hr />
        <div className="container footer-bottom text-center">
          <p>{new Date().getFullYear()} SpoonRush. All Rights Reserved</p>
        </div>
      </footer>
    </div>
  );
};

export default Footer;

//text-center text-dark p-3

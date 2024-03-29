import React from "react";
import logo from "../Images/Spoon_Logo-removebg.png";
import { Link } from "react-router-dom";
import RoleNav from "./RoleNav";

function Header() {
  const role = sessionStorage.getItem("role");

  return (
    <div>
      <nav className="navbar navbar-expand-lg custom-bg text-color">
        <div className="container-fluid text-color">
          <Link to="/" className="navbar-brand">
            <img
              src={logo}
              height="70"
              width="auto"
              className="d-inline-block align-top"
              alt=""
            />
          </Link>

          {/* logo is added */}
          {role === "ROLE_MANAGER" ? (
            <></>
          ) : (
            <>
              <button
                className="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent"
                aria-expanded="false"
                aria-label="Toggle navigation"
              >
                <span className="navbar-toggler-icon"></span>
              </button>
              <div
                className="collapse navbar-collapse"
                id="navbarSupportedContent"
              >
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                  {/* Conditionally render Menu, Contact Us, and About Us links only for ROLE_MANAGER */}
                  {role !== "ROLE_DELIVERY" && (
                    <>
                      <li className="nav-item">
                        <Link
                          to="/menu"
                          className="nav-link active"
                          aria-current="page"
                        >
                          <b className="text-color">Menu</b>
                        </Link>
                      </li>
                      <li className="nav-item">
                        <Link
                          to="/aboutus"
                          className="nav-link active"
                          aria-current="page"
                        >
                          <b className="text-color">About Us</b>
                        </Link>
                      </li>
                      <li className="nav-item">
                        <Link
                          to="/contactus"
                          className="nav-link active"
                          aria-current="page"
                        >
                          <b className="text-color">Contact Us</b>
                        </Link>
                      </li>
                    </>
                  )}
                  
                  {/* Conditionally render Profile link for ROLE_DELIVERY */}
                  {role === "ROLE_DELIVERY" && (
                    <li className="nav-item">
                      <Link
                        to="/delivery-person/profile"
                        className="nav-link active"
                        aria-current="page"
                      >
                        <b className="text-color">Profile</b>
                      </Link>
                    </li>
                  )}
                </ul>
              </div>
            </>
          )}
        </div>
        <RoleNav />
      </nav>
    </div>
  );
}

export default Header;

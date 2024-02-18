import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import Footer from "../NavbarComponent/Footer";
import "react-toastify/dist/ReactToastify.css";

const SignUp = () => {
  const navigate = useNavigate();

  const restaurant = JSON.parse(sessionStorage.getItem("active-restaurant"));

  const [user, setUser] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    mobileNo: "",
  });

  const handleUserInput = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const saveUser = (e) => {
    fetch("https://localhost:8443/users/signup", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    })
      .then((result) => {
        console.log("result", result);
        result.json().then((res) => {
          console.log(res.mesg);
          if (res.id != null) {
            console.log("we got the registration here ");

            toast.success("User Registered Successfully!", {
              position: "top-center",
              autoClose: 1500,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
            setTimeout(() => {
              window.location.href = "/user/login";
            }, 1000);
          } else {
            toast.error("Something Went Wrong!", {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
          }
        });
      })
      .catch((error) => {
        console.error(error);
        toast.error("Sry! server is down...", {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
      });
    e.preventDefault();
  };

  return (
    <div>
      <div className="mt-2 d-flex aligns-items-center justify-content-center ms-2 me-2 mb-2">
        <div
          className="form-card border-color text-color"
          style={{ width: "50rem" }}
        >
          <div className="container-fluid">
            <div
              className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center"
              style={{
                borderRadius: "0em",
                height: "45px",
              }}
            >
              <h5 className="card-title">Register Here!!!</h5>
            </div>
            <div className="card-body mt-3">
              <form className="row g-3" onSubmit={saveUser}>
                <div className="col-md-6 mb-3 text-color">
                  <label htmlFor="title" className="form-label">
                    <b>First Name</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="firstName"
                    name="firstName"
                    onChange={handleUserInput}
                    value={user.firstName}
                  />
                </div>

                <div className="col-md-6 mb-3 text-color">
                  <label htmlFor="title" className="form-label">
                    <b>Last Name</b>
                  </label>
                  <input
                    type="text"
                    className="form-control"
                    id="lastName"
                    name="lastName"
                    onChange={handleUserInput}
                    value={user.lastName}
                  />
                </div>

                <div className="col-md-6 mb-3 text-color">
                  <b>
                    <label className="form-label">Email Id</label>
                  </b>
                  <input
                    type="email"
                    className="form-control"
                    id="email"
                    name="email"
                    onChange={handleUserInput}
                    value={user.email}
                  />
                </div>
                <div className="col-md-6 mb-3">
                  <label htmlFor="quantity" className="form-label">
                    <b>Password</b>
                  </label>
                  <input
                    type="password"
                    className="form-control"
                    id="password"
                    name="password"
                    onChange={handleUserInput}
                    value={user.password}
                  />
                </div>

                <div className="col-md-6 mb-3">
                  <label htmlFor="contact" className="form-label">
                    <b>Contact No</b>
                  </label>
                  <input
                    type="number"
                    className="form-control"
                    id="mobileNo"
                    name="mobileNo"
                    onChange={handleUserInput}
                    value={user.mobileNo}
                  />
                </div>

                <div className="d-flex aligns-items-center justify-content-center">
                  <input
                    type="submit"
                    className="btn bg-color custom-bg-text"
                    value="Register User"
                  />
                </div>
                <ToastContainer />
              </form>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default SignUp;

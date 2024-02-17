import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
const AdminHeader = () => {
  let navigate = useNavigate();

  const user = sessionStorage.getItem("role");
  console.log(user);

  const restaurantLogout = () => {
    toast.success("Logged Out Successfully.!", {
      position: "top-center",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
    sessionStorage.removeItem("role");
    sessionStorage.removeItem("restaurant-jwtToken");
    window.location.reload(true);
    setTimeout(() => {
      navigate("/home");
    }, 2000);
  };

  return (
    <ul className="navbar-nav ms-auto mb-2 mb-lg-0 me-5">
      <li className="nav-item">
        <Link
          to="/restaurant/order/all"
          className="nav-link active"
          aria-current="page"
        >
          <b className="text-color">Restaurant Orders</b>
        </Link>
      </li>
      <li className="nav-item">
        <Link to="/food/add" className="nav-link active" aria-current="page">
          <b className="text-color">Add Food</b>
        </Link>
      </li>

      <li className="nav-item">
        <Link
          to="/restaurant/food/all"
          className="nav-link active"
          aria-current="page"
        >
          <b className="text-color">View My Foods</b>
        </Link>
      </li>

      <li className="nav-item">
        <Link
          to=""
          className="nav-link active"
          aria-current="page"
          onClick={restaurantLogout}
        >
          <b className="text-color">Logout</b>
        </Link>
        <ToastContainer />
      </li>
    </ul>
  );
};

export default AdminHeader;

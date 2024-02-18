import React, { useState } from "react"; // Import React and useState
import { useLocation, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import axios from 'axios'; // Import axios

const AddCartDetail = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const priceToPay = location.state.priceToPay;
  const addressId = location.state.addressId;
  const customer_jwtToken = sessionStorage.getItem("jwtToken");

  const paymentMode = ["COD"];
  
  const paymentStatus = ["UNPAID"];

  // Initialize cart state using useState hook
  const [cart, setCart] = useState({
    paymentStatus: "",
    paymentMethod: "",
  });

  // Function to handle cart input changes
  const handleCartInput = (e) => {
    setCart({ ...cart, [e.target.name]: e.target.value });
  };

  // Function to handle form submission
  const payForOrder = (e) => {
    e.preventDefault();
    axios.post("https://localhost:8443/orders/customer/place-order", {
      addressId: addressId,
      paymentStatus: "UNPAID",
      paymentMode: "COD",
    }, {
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + customer_jwtToken,
      }
    })
    .then((response) => {
      console.log(response);
      if (response.data.status === "SUCCESS") {
        toast.success(response.data.message, {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        setTimeout(() => {
          navigate("/customer/order");
        }, 2000); // Redirect after 3 seconds
      } else {
        toast.error(response.data.message, {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
      }
    })
    .catch((error) => {
      console.error(error);
      toast.error("It seems the server is down", {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });
    });
  };

  return (
    <div className="mt-2 d-flex aligns-items-center justify-content-center">
      <div className="cart form-cart border-color" style={{ width: "25rem" }}>
        <div className="cart-header bg-color custom-bg-text">
          <h5 className="cart-title text-center">Payment Details</h5>
        </div>
        <div className="cart-body text-color custom-bg">
          <form onSubmit={payForOrder}>
            <div className="mb-3">
              <label htmlFor="paymentStatus" className="form-label">
                <b>Payment Status</b>
              </label>
              <select
                id="paymentStatus"
                className="form-select"
                name="paymentStatus"
                onChange={handleCartInput}
                value={cart.paymentStatus}
                required
              >
                <option value="">Select Payment Status</option>
                {paymentStatus.map((status, index) => (
                  <option key={index} value={status}>
                    {status}
                  </option>
                ))}
              </select>
            </div>
            <div className="mb-3">
              <label htmlFor="paymentMethod" className="form-label">
                <b>Payment Method</b>
              </label>
              <select
                id="paymentMethod"
                className="form-select"
                name="paymentMethod"
                onChange={handleCartInput}
                value={cart.paymentMethod}
                required
              >
                <option value="">Select Payment Method</option>
                {paymentMode.map((method, index) => (
                  <option key={index} value={method}>
                    {method}
                  </option>
                ))}
              </select>
            </div>
            <input
              type="submit"
              className="btn custom-bg-text bg-color"
              value={"Pay Rs " + priceToPay}
            />
            <ToastContainer />
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddCartDetail;
import React, { useState } from "react"; // Import React and useState
import { useLocation, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

const AddCartDetail = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const priceToPay = location.state.priceToPay;
  const addressId = location.state.addressId;
  const customer_jwtToken = sessionStorage.getItem("jwtToken");

  const paymentMode = ["COD", "UPI"];
  
  const paymentStatus = ["COD_PAID", "UNPAID", "SUCCESSFUL", "FAILED"];

  // Initialize card state using useState hook
  const [card, setCard] = useState({
    paymentStatus: "",
    paymentMethod: "",
  });

  // Function to handle card input changes
  const handleCardInput = (e) => {
    setCard({ ...card, [e.target.name]: e.target.value });
  };

  // Function to handle form submission
  const payForOrder = (e) => {
    e.preventDefault();
    fetch("https://localhost:8443/orders/customer/place-order", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + customer_jwtToken,
      },
      body: JSON.stringify({
        addressId: addressId,
        paymentStatus: card.paymentStatus,
        paymentMode: card.paymentMethod,
      }),
    })
      .then((result) => {
        result.json().then((res) => {
          if (res.success) {
            toast.success(res.responseMessage, {
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
            toast.error(res.responseMessage || "Payment failed", {
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
      <div className="card form-card border-color" style={{ width: "25rem" }}>
        <div className="card-header bg-color custom-bg-text">
          <h5 className="card-title text-center">Payment Details</h5>
        </div>
        <div className="card-body text-color custom-bg">
          <form onSubmit={payForOrder}>
            <div className="mb-3">
              <label htmlFor="paymentStatus" className="form-label">
                <b>Payment Status</b>
              </label>
              <select
                id="paymentStatus"
                className="form-select"
                name="paymentStatus"
                onChange={handleCardInput}
                value={card.paymentStatus}
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
                onChange={handleCardInput}
                value={card.paymentMethod}
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

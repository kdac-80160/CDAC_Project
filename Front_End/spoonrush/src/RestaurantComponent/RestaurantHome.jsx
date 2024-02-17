import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import axios from "axios";
import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";

function RestaurantHome() {
  axios.defaults.headers.common[
    "Authorization"
  ] = `Bearer ${sessionStorage.getItem("jwtToken")}`;
  const [pendingOrders, setPendingOrders] = useState([]);
  const url = "https://localhost:8443/orders/restaurant";
  function fetchData() {
    axios
      .get("https://localhost:8443/orders/restaurant/pending")
      .then((result) => {
        setPendingOrders(result.data);
        console.log(result.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  useEffect(fetchData, []);

  const handleOrderAction = async (orderId, orderStatus) => {
    try {
      // Send a request to update the order status
      const response = await axios.post(url + "", {
        orderId,
        orderStatus,
      });
      console.log(response.data);
    } catch (error) {
      console.error("Error updating order status:", error);
    }
  };

  return (
    <div className="row">
      <div className="col-2">
        <NavLink
          className={"link-offset-2 link-underline link-underline-opacity-0"}
        >
          Pending Orders
        </NavLink>
        <br />
        <NavLink
          className={"link-offset-2 link-underline link-underline-opacity-0"}
        >
          Cancelled Orders
        </NavLink>
        <br />
        <NavLink
          className={"link-offset-2 link-underline link-underline-opacity-0"}
        >
          Delivered Orders
        </NavLink>
      </div>

      <div className="col-10">
        <div>
          {" "}
          <h2 className="text-center mt-2">Pending Orders</h2>
        </div>
        <div className="container card w-50 p-4 mt-4 border">
          {pendingOrders.length > 0 ? (
            <>
              {/* Render the component with data */}

              {pendingOrders.map((order, index) => (
                <div key={index}>
                  <p>
                    <strong>Order ID:</strong> {order.orderId}
                  </p>
                  <p>
                    <strong>User Name:</strong> {order.userName}
                  </p>
                  <p>
                    <strong>Order Date:</strong>{" "}
                    {new Date(order.orderDate).toLocaleString()}
                  </p>
                  <p>
                    <strong>Log:</strong> {order.orderLog}
                  </p>
                  <p>
                    <strong>Total Amount:</strong> Rs. {order.totalAmount}
                  </p>
                  <p>
                    <strong>Payment Status:</strong> {order.payStatus}
                  </p>
                  <p>
                    <strong>Order Status:</strong> {order.orderStatus}
                  </p>
                  {/* ... (rest of the properties) */}
                  <h2 className="mt-4 mb-4">Ordered Items</h2>
                  <ul className="list-group">
                    {order.orderedItemList.map((foodItem, itemIndex) => (
                      <li key={itemIndex} className="list-group-item">
                        <strong>{foodItem.item.itemName}</strong> - Quantity:{" "}
                        {foodItem.quantity}
                      </li>
                    ))}
                  </ul>
                  <div className="mt-3">
                    <button
                      className="btn btn-success m-2"
                      onClick={() => handleOrderAction(order.id, "ACCEPT")}
                    >
                      Accept
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleOrderAction(order.id, "REJECT")}
                    >
                      Reject
                    </button>
                  </div>
                </div>
              ))}
            </>
          ) : (
            // Display a loading message or handle empty state
            <p>Loading...</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default RestaurantHome;

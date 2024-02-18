import React, { useState, useEffect } from "react";
import axios from "axios";
import '../Styles/viewOrder.css';

const Order = () => {
  const customer_jwtToken = sessionStorage.getItem("jwtToken");
  const [orders, setOrders] = useState([]);
  const [viewType, setViewType] = useState(null);

  useEffect(() => {
    fetchOrders(viewType);
  }, [viewType]);

  const fetchOrders = async (type) => {
    try {
      const endpoint =
        type === "upcoming"
          ? "https://localhost:8443/orders/customer/upcoming"
          : "https://localhost:8443/orders/customer/previous";
      const response = await axios.get(endpoint, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          Authorization: "Bearer " + customer_jwtToken,
        },
      });
      setOrders(response.data);
    } catch (error) {
      console.error("Error fetching orders:", error);
    }
  };

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(epochTime);
    return date.toLocaleString(); // Adjust the format as needed
  };

  const getStatusClassName = (status) => {
    switch (status) {
      case 'PENDING':
      case 'WAITING':
      case 'ON_THE_WAY':
      case 'ACCEPTED':
      case 'PREPARING':
      case 'READY_FOR_DELIVERY':
        return 'warning'; // Set the class name based on the status
      default:
        return ''; // Default class name
    }
  };

  const shouldRenderCancelButton = (status) => {
    return ['PENDING', 'WAITING', 'ON_THE_WAY', 'ACCEPTED', 'PREPARING', 'READY_FOR_DELIVERY'].includes(status);
  };

  const cancelOrder = async (orderId) => {
    try {
      const response = await axios.post(
        `https://localhost:8443/orders/customer/cancel/${orderId}`,
        null,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            Authorization: "Bearer " + customer_jwtToken,
          },
        }
      );
      // Handle success or update state if needed
    } catch (error) {
      console.error("Error canceling order:", error);
      // Handle error
    }
  };

  const calculateETA = (orderTime) => {
    const orderTimestamp = new Date(orderTime).getTime();
    const etaTimestamp = orderTimestamp + (30 * 60000); // Add 30 minutes
    const currentTimestamp = new Date().getTime();
    const remainingMilliseconds = etaTimestamp - currentTimestamp;
    const remainingMinutes = Math.ceil(remainingMilliseconds / 60000); // Converting milliseconds to minutes and rounding up
    return Math.max(remainingMinutes, 0); // Return 0 if ETA has already passed
  };

  return (
    <div className="table-container">
      <table className="order-table">
        <thead>
          <tr>
            <th className="header-cell" colSpan="2">Order Details</th>
          </tr>
        </thead>
        {orders.map((order) => (
          <tbody key={order.orderId}>
            <tr><td colSpan={1} >   </td>
            <td colSpan={1} className="goToRight"> {viewType === "upcoming" && shouldRenderCancelButton(order.orderStatus) && (
              <div>
                <button onClick={() => cancelOrder(order.orderId)} className="btn btn-danger">Cancel</button>
              </div>
            )}</td></tr>
            <tr>
              <td className="order-details">
                <div className="widt">
                  <strong>Order ID:</strong> {order.orderId}
                </div>
                <div>
                  <strong>Food:</strong>
                  <ul>
                    {order.orderedItemList.map((item, index) => (
                      <li key={index}>{item.item.itemName}</li>
                    ))}
                  </ul>
                </div>
                <div>
                  <strong>Price:</strong> ₹{order.totalAmount.toFixed(2)}
                </div>
                <div>
                  <strong>Order Time:</strong> {formatDateFromEpoch(order.orderDate)}
                </div>
              </td>
              <td className="order-status">
                <div className={`order-status ${getStatusClassName(order.orderStatus)}`}>
                  <strong>Delivery Status: </strong> {order.orderStatus}</div>
                <div>
                  <strong>Delivery Contact:</strong> {order.deliveryGuyNumber}
                </div>
                <div>
                  <strong>Delivery Time:</strong> {formatDateFromEpoch(order.deliveryTime)}
                </div>
                <div>
                  <strong>Quantity:</strong> {order.orderedItemList.length}
                </div>
                {/* {viewType === "upcoming" && shouldRenderCancelButton(order.orderStatus) && (
                  <div>
                    <button onClick={() => cancelOrder(order.orderId)} className="btn btn-warning buttonMargin">Cancel</button>
                  </div>
                )} */}
              </td>
            </tr>

          </tbody>))}
      </table>
      <div className="order-buttons">
        <button onClick={() => setViewType("upcoming")} className="btn btn-outline-success mystyle">Upcoming Orders</button>
        <button onClick={() => setViewType("previous")} className="btn btn-outline-success mystyle">Previous Orders</button>
      </div>
    </div>
  );
};

export default Order;

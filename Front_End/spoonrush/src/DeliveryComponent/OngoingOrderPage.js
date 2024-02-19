import React, { useState, useEffect } from 'react';
import axios from 'axios';

const OngoingOrderPage = ({ history }) => {
  const customer_jwtToken = sessionStorage.getItem("jwtToken");
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetchOrders();
  }, []);

  const fetchOrders = async () => {
    try {
      const response = await axios.get('https://localhost:8443/orders/delivery/on-going', {
        headers: {
          Authorization: "Bearer " + customer_jwtToken, 
        },
      });
      setOrders(response.data);
    } catch (error) {
      console.error('Error fetching orders:', error);
    }
  };

  const handleDelivered = async (orderId) => {
    try {
      // Update the order status to "DELIVERED"
      await axios.patch(`https://localhost:8443/orders/delivery/change-status`, {
        id: orderId,
        orderStatus: "DELIVERED",
      }, {
        headers: {
          Authorization: "Bearer " + customer_jwtToken,
        },
      });

      // Fetch orders again after updating status
      await fetchOrders();
    } catch (error) {
      console.error('Error delivering order:', error);
    }
  };

  return (
    <div>
      <h2>Order Dashboard</h2>
      <table className="table">
        <thead>
          <tr>
            <th>Order ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Address</th>
            <th>Total Amount</th>
            <th>Order Status</th>
            <th>Payment Mode</th>
            <th>Payment Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {orders.map(order => (
            <tr key={order.orderId}>
              <td>{order.orderId}</td>
              <td>{order.firstName}</td>
              <td>{order.lastName}</td>
              <td>{order.address.street}, {order.address.city}</td>
              <td>{order.totalAmount}</td>
              <td>{order.orderStatus}</td>
              <td>{order.payMode}</td>
              <td>{order.payStatus}</td>
              <td>
                <button onClick={() => handleDelivered(order.orderId)} className="btn btn-success">
                  Delivered
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default OngoingOrderPage;

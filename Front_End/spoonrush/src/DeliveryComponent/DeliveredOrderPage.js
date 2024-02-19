import React, { useState, useEffect } from 'react';
import axios from 'axios';

const DeliveredPage = () => {
  const customer_jwtToken = sessionStorage.getItem("jwtToken");
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetchDeliveredOrders();
  }, []);

  const fetchDeliveredOrders = async () => {
    try {
      const response = await axios.get('https://localhost:8443/orders/delivery/delivered', {
        headers: {
          Authorization: "Bearer " + customer_jwtToken,
        },
      });
      setOrders(response.data);
      console.log(response.data);
    } catch (error) {
      console.error('Error fetching delivered orders:', error);
    }
  };

  return (
    <div>
      <h2>Delivered Orders</h2>
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
          </tr>
        </thead>
        <tbody>
          {orders.map(order => (
            <tr key={order.orderId}>
              <td>{order.orderId}</td>
              <td>{order.firstName}</td>
              <td>{order.lastName}</td>
              <td>{order.address.houseFlatNo},{order.address.streetName}, {order.address.locality.localityName}</td>
              <td>{order.totalAmount}</td>
              <td>{order.orderStatus}</td>
              <td>{order.payMode}</td>
              <td>{order.payStatus}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default DeliveredPage;

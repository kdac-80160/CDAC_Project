import axios from "axios";
import React, { useEffect, useState } from "react";

function CancelledOrders() {
  axios.defaults.headers.common[
    "Authorization"
  ] = `Bearer ${sessionStorage.getItem("jwtToken")}`;

  const [deliveredOrders, setDeliveredOrders] = useState([]);

  const timeUtil = {
    day: "numeric",
    month: "numeric",
    year: "numeric",
    hour: "numeric",
    minute: "numeric",
    second: "numeric",
    hour12: true,
  };

  function fetchData() {
    axios
      .get("https://localhost:8443/orders/restaurant/delivered")
      .then((result) => {
        setDeliveredOrders(result.data);
        console.log(result.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }
  useEffect(fetchData, []);


  return (
    <>
      <div className="col-10">
        <div>
          {" "}
          <h2 className="text-center mt-2">Delivered Orders</h2>
        </div>
        <div className="container card w-50 p-4 mt-4 border">
          {deliveredOrders.length > 0 ? (
            <>
              {/* Render the component with data */}

              {deliveredOrders.map((order, index) => (
                <div key={index} className="border m-2">
                  <p>
                    <strong>Order ID:</strong> {order.orderId}
                  </p>
                  <p>
                    <strong>User Name:</strong> {order.userName}
                  </p>
                  <p>
                    <strong>Order Date:</strong>{" "}
                    {new Date(order.orderDate).toLocaleString(
                      "en-US",
                      timeUtil
                    )}
                  </p>
                  <p>
                    <strong>Log:</strong>{" "}
                    {order.orderLog != null?<>{new Date(order.orderLog).toLocaleString('en-US',timeUtil)}</> : <>No Logs</>}
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
                  <h4 className="mt-4 mb-4">Ordered Items</h4>
                  <ul className="list-group">
                    {order.orderedItemList.map((foodItem, itemIndex) => (
                      <li key={itemIndex} className="list-group-item">
                        <strong>{foodItem.item.itemName}</strong> - Quantity:{" "}
                        {foodItem.quantity}
                      </li>
                    ))}
                  </ul>
                </div>
              ))}
            </>
          ) : (
            // Display a loading message or handle empty state
            <p>
              {deliveredOrders.length > 0 ? (
                <>Loading orders....</>
              ) : (
                <>
                  <strong style={{ fontSize: 30 }}>
                    There are no delivered orders.
                  </strong>
                </>
              )}
            </p>
          )}
        </div>
      </div>
    </>
  );
}

export default CancelledOrders;

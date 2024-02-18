import axios from "axios";
import { useEffect, useState } from "react";

function PendingOrders() {


    axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${sessionStorage.getItem("jwtToken")}`;
      
      const [pendingOrders, setPendingOrders] = useState([]);

      const timeUtil = {
        day: 'numeric',
        month: 'numeric',
        year: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
        second: 'numeric',
        hour12: true
      };
    
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
    
      const handleOrderAction = async (id, orderStatus) => {
        try {
          console.log(`id : ${id}+ status : ${orderStatus}`)
          // Send a request to update the order status
          const response = await axios.post(url + "/change-status", {
            id,
            orderStatus,
          });
          console.log(response.data);
          fetchData();
        } catch (error) {
          console.error("Error updating order status:", error);
        }
      };
    
    return ( <>
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
                <div key={index} className="border m-2">
                  <p>
                    <strong>Order ID:</strong> {order.orderId}
                  </p>
                  <p>
                    <strong>User Name:</strong> {order.userName}
                  </p>
                  <p>
                    <strong>Order Date:</strong>{" "}
                    {new Date(order.orderDate).toLocaleString('en-US',timeUtil)}
                  </p>
                  <p>
                    <strong>Log:</strong> {new Date(order.orderLog).toLocaleString('en-US',timeUtil)}
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
                  <div className="mt-3">
                    <button
                      className="btn btn-success m-2"
                      onClick={() => handleOrderAction(order.orderId, "ACCEPTED")}
                    >
                      Accept
                    </button>
                    <button
                      className="btn btn-danger"
                      onClick={() => handleOrderAction(order.orderId, "REJECTED")}
                    >
                      Reject
                    </button>
                  </div>
                </div>
              ))}
            </>
          ) : (
            // Display a loading message or handle empty state
            <p>{pendingOrders.length>0 ?<>Loading orders....</> :<><strong style={{fontSize:30}}>There are no pending orders.</strong></>}</p>
          )}
        </div>
      </div>
    </>);
}

export default PendingOrders;
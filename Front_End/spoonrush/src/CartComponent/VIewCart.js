import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";

const ViewCart = () => {
  const customer_jwtToken = sessionStorage.getItem("jwtToken");

  const [carts, setCarts] = useState([]);
  const [cartAmount, setCartAmount] = useState("0.0");

  let navigate = useNavigate();

  useEffect(() => {
    const getAllCart = async () => {
      const allCart = await retrieveCart();
      if (allCart) {
        setCarts(allCart);

        if (allCart.totalCartAmount) {
          setCartAmount(allCart.totalCartAmount);
        }
      }
    };

    getAllCart();
  }, []);

  const retrieveCart = async () => {
    const response = await axios.get(
      //url dalna hai
      "https://localhost:8443/cart/all",
      {
        headers: {
          Authorization: "Bearer " + customer_jwtToken,
        },
      }
    );
    console.log(response.data);
    addTotal(response.data);
    return response.data;
  };

  const deleteCart = (e) => {
    console.log(e);
    fetch(`https://localhost:8443/cart/remove/${e}`, {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + customer_jwtToken,
      },
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
              window.location.reload(true);
            }, 1000); // Redirect after 3 seconds
          } else if (!res.success) {
            toast.error(res.responseMessage, {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
            setTimeout(() => {
              window.location.reload(true);
            }, 1000); // Redirect after 3 seconds
          }
        });
      })
      .catch((error) => {
        console.error(error);
        toast.error("It seems server is down", {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        setTimeout(() => {
          window.location.reload(true);
        }, 1000); // Redirect after 3 seconds/
      });
  };

  const incrementCart = (e) => {
    fetch(`https://localhost:8443/cart/add/${e}`, {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + customer_jwtToken,
      },
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
              window.location.reload(true);
            }, 1000); // Redirect after 3 seconds
          } else if (!res.success) {
            toast.error(res.responseMessage, {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
            setTimeout(() => {
              window.location.reload(true);
            }, 1000); // Redirect after 3 seconds
          }
        });
      })
      .catch((error) => {
        console.error(error);
        toast.error("It seems server is down", {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        setTimeout(() => {
          window.location.reload(true);
        }, 1000); // Redirect after 3 seconds
      });
  };

  const decrementCart = (e) => {
    console.log(e);

    fetch(`https://localhost:8443/cart/remove/${e}`, {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + customer_jwtToken,
      },
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
              window.location.reload(true);
            }, 1000); // Redirect after 3 seconds
          } else if (!res.success) {
            toast.error(res.responseMessage, {
              position: "top-center",
              autoClose: 1000,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              progress: undefined,
            });
            setTimeout(() => {
              window.location.reload(true);
            }, 1000); // Redirect after 3 seconds
          }
        });
      })
      .catch((error) => {
        console.error(error);
        toast.error("It seems server is down", {
          position: "top-center",
          autoClose: 1000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        setTimeout(() => {
          window.location.reload(true);
        }, 1000); // Redirect after 3 seconds
      });
  };

  const checkout = (e) => {
    e.preventDefault();

    if (carts === null || carts.length < 1) {
      toast.error("No Foods In Cart To Order!!!", {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

      return;
    }
    navigate("/customer/order/payment", {
      state: { priceToPay: cartAmount },
    });
  };

  const addTotal = (carts) => {
    let total = 0;
    carts.map((cart) => (total += cart.item.price * cart.quantity));
    setCartAmount(total);
  };

  return (
    <div className="container">
      <div className="mt-3">
        <div
          className="card form-card ms-2 me-2 mb-5 shadow-lg"
          style={{
            height: "40rem",
          }}
        >
          <div
            className="card-header custom-bg-text text-center bg-color"
            style={{
              borderRadius: "0em",
              height: "50px",
            }}
          >
            <h2>Cart</h2>
          </div>
          <div
            className="card-body"
            style={{
              overflowY: "auto",
            }}
          >
            <div className="table-responsive">
              <table className="table table-hover text-color text-center">
                <thead className="table-bordered border-color bg-color custom-bg-text">
                  <tr>
                    <th scope="col">Food</th>
                    <th scope="col">Food Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Action</th>
                  </tr>
                </thead>
                <tbody>
                  {carts.map((cart) => {
                    return (
                      <tr>
                        <td>
                          <img
                            src={cart.item.imagePath}
                            class="img-fluid"
                            alt="food_pic"
                            style={{
                              maxWidth: "90px",
                            }}
                          />
                        </td>
                        <td>
                          <b>{cart.item.itemName}</b>
                        </td>
                        <td>
                          <b>{cart.item.category}</b>
                        </td>
                        <td>
                          <b>{cart.item.price}</b>
                        </td>
                        <td>
                          <button
                            onClick={() => decrementCart(cart.item.id)}
                            className="btn btn-sm bg-color custom-bg-text me-2"
                          >
                            -
                          </button>
                          <b>{cart.quantity}</b>
                          <button
                            onClick={() => incrementCart(cart.item.id)}
                            className="btn btn-sm bg-color custom-bg-text ms-2"
                          >
                            +
                          </button>
                        </td>
                        <td>
                          <button
                            onClick={() => deleteCart(cart.item.id)}
                            className="btn btn-sm bg-color custom-bg-text ms-2"
                          >
                            Delete
                          </button>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>
            </div>
          </div>
          <div className="card-footer custom-bg">
            <div className="float-right">
              <div
                className="text-color me-2"
                style={{
                  textAlign: "right",
                }}
              >
                <h5>Total Price: &#8377; {cartAmount}/-</h5>
              </div>

              <div className="float-end me-2">
                <button
                  type="submit"
                  className="btn bg-color custom-bg-text mb-3"
                  onClick={checkout}
                >
                  Checkout
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewCart;

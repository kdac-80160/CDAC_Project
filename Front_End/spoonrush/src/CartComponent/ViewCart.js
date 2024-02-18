import { useState, useEffect } from "react";
import axios from "axios";
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";




const ViewCart = () => {
  const [addresses, setAddresses] = useState([]);
  const [selectedAddress, setSelectedAddress] = useState("");
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
    fetchAddresses();
    getAllCart();
  }, []);

  const fetchAddresses = async () => {
    const response = await axios.get("https://localhost:8443/address/all",
        {
            headers: {
                Authorization: "Bearer " + customer_jwtToken, 
            },
        }
    );
    console.log(response.data);
    setAddresses(response.data);
};

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
    
    if ((carts === null || carts.length < 1 )) {
      
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
    }else if(selectedAddress === "")
    { 
      toast.error("Please select The Address", {
        position: "top-center",
        autoClose: 1000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
      });

    }
    else
    {
    navigate("/customer/order/payment", {
      state: { priceToPay: cartAmount,
              addressId : selectedAddress.id
              
       },
    });
  }
  };

  const addTotal = (carts) => {
    let total = 0;
    carts.map((cart) => (total += cart.item.price * cart.quantity));
    setCartAmount(total);
  };

  const handleSelectAddress = (address) => {
    setSelectedAddress(address);
   
};

const formatAddress = (address) => {
    const parts = [
        address.houseFlatNo,
        address.streetName,
        address.landmark,
        address.locality.localityName,
        address.type,
        address.mobileNo
    ];

    const filteredParts = parts.filter(part => part);
    return filteredParts.join(', ');
};

  return (
    <div className="container"
    style={{ display: 'flex', justifyContent: 'space-between' }}
      >
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
      <div >
          <div className="mt-2 d-flex aligns-items-center justify-content-center">
                <div className="form-card border-color" style={{ width: "37rem" }}>
                    <h2 className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center" style={{ borderRadius: "0em", height: "38px" }}>All Addresses</h2>
                    <div className="card-body mt-3 d-flex justify-content-center">
                        <div>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Select</th>
                                        <th>Address</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {addresses.map((address, index) => (
                                        <tr key={index}>
                                            <td>
                                                <input
                                                    type="radio"
                                                    name="selectedAddress"
                                                    value={address.id}
                                                    onClick={() => handleSelectAddress(address)}
                                                />
                                            </td>
                                            <td>{formatAddress(address)}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                            <br/>
                        </div>   
                    </div>
                    {!selectedAddress && (
                        <div className="add-address-button text-center">
                            <Link to="/addaddress">
                                <button className="btn bg-color custom-bg-text">Add Address</button>
                            </Link>
                        </div>
                    )}
                </div>
            </div>
      </div>
    </div>
  );
};

export default ViewCart;

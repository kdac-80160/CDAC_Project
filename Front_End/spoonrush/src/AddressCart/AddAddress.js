import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

const AddAddress = () => {
    const navigate =useNavigate();
    const customer_jwtToken = sessionStorage.getItem("jwtToken");
    const [localities, setLocalities] = useState([]);
    const [address, setAddress] = useState({
        houseFlatNo: '',
        streetName: '',
        type: '',
        mobileNo: '',
        landmark: '',
        localityId: '',
        moreDetails: '',
    });

    useEffect(() => {
        fetchLocalities();
    }, []);

    const fetchLocalities = async () => {
        try {
            const response = await axios.get('https://localhost:8443/locality/all');
            setLocalities(response.data);
        } catch (error) {
            console.error('Error fetching localities:', error);
        }
    };

    const handleChange = (e) => {
        setAddress({
            ...address,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log(address);
        fetch("https://localhost:8443/address/add", {
            method: "POST",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json",
              Authorization: "Bearer " + customer_jwtToken,
            },
            body: JSON.stringify(address),
          }).then((result) => {
            result.json().then((res) => {
              if (res !== null) {
                console.log(res);
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
                  navigate("/customer/cart");
                }, 2000); // Redirect after 3 seconds
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
                // setTimeout(() => {
                //   window.location.reload(true);
                // }, 2000); // Redirect after 3 seconds
              } else {
                toast.error("It Seems Server is down!!!", {
                  position: "top-center",
                  autoClose: 1000,
                  hideProgressBar: false,
                  closeOnClick: true,
                  pauseOnHover: true,
                  draggable: true,
                  progress: undefined,
                });
                // setTimeout(() => {
                //   window.location.reload(true);
                // }, 2000); // Redirect after 3 seconds
              }
            });
          });
       
    };
    


    const typeOptions = ["HOME", "WORK", "OTHER", "DEFAULT"];

   
    const mobileRegex = /^[0-9]{10}$/;

    return (
        <div className="mt-2 d-flex aligns-items-center justify-content-center">
            <div className="form-card border-color" style={{ width: "37rem" }}>
                <h2 className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center" style={{ borderRadius: "0em", height: "38px" }}>Add Address</h2>
                <div className="card-body mt-3 d-flex justify-content-center">
                    <form onSubmit={handleSubmit}>
                        <div className="mb-3 text-color">
                            <label>House/Flat No</label>
                            <input type="text" name="houseFlatNo" value={address.houseFlatNo} onChange={handleChange} className="form-control" required />
                        </div>
                        <div className="mb-3 text-color">
                            <label>Landmark</label>
                            <input type="text" name="landmark" value={address.landmark} onChange={handleChange} className="form-control" required />
                        </div>
                        <div className="mb-3 text-color">
                            <label>Street Name</label>
                            <input type="text" name="streetName" value={address.streetName} onChange={handleChange} className="form-control" required />
                        </div>
                        <div className="mb-3 text-color">
                            <label>Mobile No</label>
                            <input type="text" name="mobileNo" value={address.mobileNo} onChange={handleChange} className={`form-control ${!mobileRegex.test(address.mobileNo) && 'is-invalid'}`} required/>
                            {!mobileRegex.test(address.mobileNo) && (
                                <div className="invalid-feedback">
                                    Please enter a valid 10-digit mobile number.
                                </div>
                            )}
                        </div>
                        <div className="mb-3 text-color" >
                            <label>More Details</label>
                            <input type="text" name="moreDetails" value={address.moreDetails} onChange={handleChange} className="form-control" required/>
                        </div>
                        <div className="mb-3 text-color">
                            <label>Locality</label>
                            <select name="localityId" value={address.localityId} onChange={handleChange} className="form-control" required>
                                <option value="">Select Locality</option>
                                {localities.map(locality => (
                                    <option key={locality.id} value={locality.id}>{locality.localityName}</option>
                                ))}
                            </select>
                        </div>
                        <div className="mb-3 text-color">
                            <label>Select Type</label>
                            <select name="type" value={address.selectedType} onChange={handleChange} className="form-control" required>
                                <option value="">Select Type</option>
                                {typeOptions.map(type => (
                                    <option key={type} value={type}>{type}</option>
                                ))}
                            </select>
                        </div>
                        <button type="submit" className="btn bg-color custom-bg-text">Add Address</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default AddAddress;


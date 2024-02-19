import React from 'react';
import deliveryBoyImage from './delivery-boy-image.jpg'; // Import the image

const DeliveryBoyProfile = () => {
  const deliveryBoy = {
    name: 'Siddu Tiwari',
    aadharCardNumber: '1234 5678 9012',
    mobileNumber: '+1234567890'
  };

  return (
    <div style={{ textAlign: 'center', marginTop: '20px' }}>
      <h2>Delivery Boy Profile</h2>
      <img src={deliveryBoyImage} alt="Delivery Boy" style={{ maxWidth: '200px', height: 'auto', borderRadius: '50%', marginBottom: '20px' }} />
      <div style={{ fontSize: '18px' }}>
        <h3>Name: {deliveryBoy.name}</h3>
        <p>Aadhar Card Number: {deliveryBoy.aadharCardNumber}</p>
        <p>Mobile Number: {deliveryBoy.mobileNumber}</p>
      </div>
    </div>
  );
};

export default DeliveryBoyProfile;

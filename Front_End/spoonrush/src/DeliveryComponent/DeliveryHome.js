import React from 'react';
import { Link, Outlet } from 'react-router-dom';

const Home = () => {
  return (
    <div style={containerStyle}>
      <div style={navStyle}>
        <Link to="/home/delivery/new-order" style={linkStyle}>New Order</Link>
      </div>
      <div style={navStyle}>
        <Link to="/home/delivery/delivered-order" style={linkStyle}>Delivered Order</Link>
      </div>
      <div style={navStyle}>
        <Link to="/home/delivery/on-going-order" style={linkStyle}>On-going Order</Link>
      </div>
      <Outlet />
    </div>
  );
};

const containerStyle = {
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
};

const navStyle = {
  background: '#ffc0cb', // Light pink background color
  padding: '15px',
  borderRadius: '8px',
  marginBottom: '15px',
  width: '250px', // Width of each box
  textAlign: 'center',
};

const linkStyle = {
  textDecoration: 'none',
  color: '#333', // Dark text color
  fontSize: '18px',
  transition: 'color 0.3s ease-in-out',
  cursor: 'pointer', // Add cursor pointer
};

linkStyle[':hover'] = {
  color: '#007bff', // Blue color on hover
};

export default Home;

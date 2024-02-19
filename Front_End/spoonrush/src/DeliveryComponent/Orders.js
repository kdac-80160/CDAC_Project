import React from 'react';
import { Link, Outlet } from 'react-router-dom';

const Home = () => {
  return (
    <div>
      <nav style={navStyle}>
        <ul style={ulStyle}>
          <li style={liStyle}>
            <Link to="/new-order" style={linkStyle}>New Order</Link>
          </li>
          <li style={liStyle}>
            <Link to="/delivered-order" style={linkStyle}>Delivered Order</Link>
          </li>
          <li style={liStyle}>
            <Link to="/on-going-order" style={linkStyle}>On-going Order</Link>
          </li>
        </ul>
      </nav>
      <Outlet />
    </div>
  );
};

const navStyle = {
  background: '#333',
  color: '#fff',
  textAlign: 'center',
  padding: '10px',
};

const ulStyle = {
  listStyle: 'none',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
};

const liStyle = {
  margin: '0 10px',
};

const linkStyle = {
  color: '#fff',
  textDecoration: 'none',
  padding: '8px 12px',
  border: '1px solid #fff',
  borderRadius: '5px',
};

export default Home;

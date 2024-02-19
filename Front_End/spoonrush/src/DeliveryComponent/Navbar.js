import React from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark" style={{ backgroundColor: '#4CAF50' }}>
      <div className="container-fluid">
        <Link className="navbar-brand" to="/" style={{ color: '#FFFFFF' }}>Delivery App</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <Link className="nav-link" to="/orders" style={{ color: '#FFFFFF' }}>Orders</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/profile" style={{ color: '#FFFFFF' }}>Profile</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/order-history" style={{ color: '#FFFFFF' }}>Order History</Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;

import React, { useState } from 'react';
import axios from 'axios';
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import { ToastContainer,toast } from 'react-toastify';
const ImageComponent = () => {
  const [formData, setFormData] = useState({
    itemName: '',
    description: '',
    price: null,
    category: null,
    subCategory: null,
    avgRating: null,
    image: null,
  });

  axios.defaults.headers.common[
    "Authorization"
  ] = `Bearer ${sessionStorage.getItem("jwtToken")}`;

  const handleInputChange = (e) => {
    const { name, value, files } = e.target;

    setFormData((prevData) => ({
      ...prevData,
      [name]: name === 'image' ? files[0] : value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('https://localhost:8443/fooditems/add', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
      .then((response) => {
      
      })
      .catch((error) => {
        console.error('Error:', error);
      });
    // Perform form submission logic here
    console.log('Form Data:', formData);
  };

  return (<>
  <div className="col-10 mt-3">
  <div className="text-center mb-4">
  <h3 className="text-success fw-bold">Add an item to the Food Menu</h3>
</div>
    <form onSubmit={handleSubmit} className="mx-auto" style={{ maxWidth: '600px', boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)', padding: '20px', borderRadius: '8px' }}>
      <div className="mb-3">
        <label className="form-label">Item Name:</label>
        <input type="text" name="itemName" value={formData.itemName} onChange={handleInputChange} className="form-control" required />
      </div>

      <div className="mb-3">
        <label className="form-label">Description:</label>
        <input type="text" name="description" value={formData.description} onChange={handleInputChange} className="form-control" required />
      </div>

      <div className="mb-3">
        <label className="form-label">Price:</label>
        <input type="number" name="price" value={formData.price || ''} onChange={handleInputChange} className="form-control" required />
      </div>

      <div className="mb-3">
        <label className="form-label">Category:</label>
        <select name="category" value={formData.category} onChange={handleInputChange} className="form-select" required>
          <option value="VEG">Veg</option>
          <option value="NON_VEG">Non-Veg</option>
        </select>
      </div>

      <div className="mb-3">
        <label className="form-label">SubCategory:</label>
        <select name="subCategory" value={formData.subCategory} onChange={handleInputChange} className="form-select" required>
        <option value="SOUTH_INDIAN">South Indian</option>
        <option value="MAIN_COURSE">Main Course</option>
        <option value="CHINESE">Chinese</option>
        <option value="BEVERAGES">Beverages</option>
        <option value="DESSERTS">Desserts</option>
        <option value="ADD_ONS">Add Ons</option>
        <option value="FAST_FOOD">Fast Food</option>
        <option value="APPETIZERS">Appetizers</option>
        <option value="SALADS">Salads</option>
        <option value="SANDWICHES">Sandwiches</option>
        <option value="BURGERS">Burgers</option>
        <option value="PIZZAS">Pizzas</option>
        <option value="PASTA">Pasta</option>
        <option value="WRAPS">Wraps</option>
        <option value="RICE_BOWLS">Rice Bowls</option>
        </select>
      </div>

      <div className="mb-3">
        <label className="form-label">Average Rating:</label>
        <input type="number" name="avgRating" value={formData.avgRating || ''} onChange={handleInputChange} className="form-control" required />
      </div>

      <div className="mb-3">
        <label className="form-label">Image:</label>
        <input type="file" name="image" onChange={handleInputChange} className="form-control" required />
      </div>

      <button type="submit" className="btn btn-success" style={{ width: '100%' }}>Add Item</button>
    </form>
    </div>
  </>
  );
};

export default ImageComponent;

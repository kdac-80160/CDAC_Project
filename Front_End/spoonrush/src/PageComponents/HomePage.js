
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import Carousel from './Carousel';
import Footer from '../NavbarComponent/Footer';

function HomePage() {
    //get user id from url
     const { categoryId , categoryName} =useParams();

     const [foods , setFoods]=useState([]);
     const [searchText ,setSearchText] =useState("");
     const [tempSearchText ,setTempSearchText]=useState("");

  useEffect(()=>{},[]);

  const searchFoods = () => {
     
  };


  return (
    <div className="container-fluid mb-2">
        <Carousel/> 
      <div className="d-flex aligns-items-center justify-content-center mt-5">
        <form class="row g-3">
          <div class="col-auto">
            <input
              type="text"
              class="form-control"
              id="inputPassword2"
              placeholder="Enter Food Name..."
              onChange={(e) => setTempSearchText(e.target.value)}
              style={{
                width: "350px",
              }}
              value={tempSearchText}
              required
            />
          </div>
          <div class="col-auto">
            <button
              type="submit"
              class="btn bg-color custom-bg-text mb-3"
              onClick={searchFoods}
            >
              Search
            </button>
          </div>
        </form>
      </div>

      <div className="col-md-12 mt-3 mb-5">
        <div className="row row-cols-1 row-cols-md-4 g-4">
          {foods.map((food) => {
           //food card detail maped
          })}
        </div>
      </div>
      <hr />
     <Footer/>
    </div>
  )
}

export default HomePage

import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Carousel from "./Carousel";
import Footer from "../NavbarComponent/Footer";
import axios from "axios";
import FoodCard from "../FoodComponents/FoodCard";
import "../Styles/Restaurant.css";
import DeliveryHome from "../DeliveryComponent/DeliveryHome";
import RestaurantHome from "../RestaurantComponent/RestaurantHome";

function HomePage() {
  const role = sessionStorage.getItem("role");
  const { categoryId } = useParams();
  const [foods, setFoods] = useState([]);
  const [searchText, setSearchText] = useState("");
  const [tempSearchText, setTempSearchText] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        let response;
        if (categoryId == null && searchText === "") {
          response = await axios.get(
            `https://localhost:8443/fooditems/popular`
          );
        } else if (searchText) {
          response = await axios.get(
            `http://localhost:8080/food/search?foodName=${searchText}`
          );
        }
        if (response.data) {
          setFoods(response.data);
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, [categoryId, searchText]);

  const searchFoods = (e) => {
    e.preventDefault();
    setSearchText(tempSearchText);
  };

  if (role === "ROLE_MANAGER") {
    return <><RestaurantHome />
    <Footer />
    </>;
  } else if (role === "ROLE_DELIVERY") {
    return <DeliveryHome />;
  } else {
    return (
      <div className="container-fluid mb-2 page-container">
        <Carousel />
        <div className="d-flex aligns-items-center justify-content-center mt-5">
          <form className="row g-3">
            <div className="col-auto">
              <input
                type="text"
                className="form-control"
                id="inputPassword2"
                placeholder="Enter Food Name..."
                onChange={(e) => setTempSearchText(e.target.value)}
                style={{ width: "350px" }}
                value={tempSearchText}
                required
              />
            </div>
            <div className="col-auto">
              <button
                type="submit"
                className="btn bg-color custom-bg-text mb-3"
                onClick={searchFoods}
              >
                Search
              </button>
            </div>
          </form>
        </div>
        <div className="col-md-12 mt-3 mb-5">
          <div className="row row-cols-1 row-cols-md-4 g-4">
            {foods.map((food) => (
              <FoodCard item={food} key={food.id} />
            ))}
          </div>
        </div>
        <hr />
        <Footer />
      </div>
    );
  }
}

export default HomePage;

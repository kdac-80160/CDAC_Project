
import axios from "axios";
import { useEffect, useState } from "react";
import FoodCard from "./FoodCard";


const Food = () => {

  const [foods, setFoods] = useState([]);

  const retrieveFood = async () => {
    const response = await axios.get(
      "https://localhost:8443/fooditems/",
    );

    return response.data;
  };

  useEffect(() => {
    const getFood = async () => {
      const retrievedFood = await retrieveFood();
     console.log(retrievedFood);
      setFoods(retrievedFood);
    };
    getFood();
  }, []);

  
  return (
    <div className="container-fluid">
      <div className="row mt-2">
        <div className="col-md-12">
          <h2 className="text-color">Foods:</h2>
          <div className="row row-cols-1 row-cols-md-4 g-4">
            {foods.map((food) => {
              return <FoodCard item={food} key={food.id} />;
            })}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Food;
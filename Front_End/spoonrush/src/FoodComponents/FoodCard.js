import React from "react";
import ItemCounter from "../Styles/ItemCounter";

const FoodCard = (food) => {
  console.log(food.item.itemName);

  const imageUrl = "https://localhost:8443/";

  const descriptionToShow = (description, maxLength) => {
    if (!description) {
      return "";
    }

    if (description.length <= maxLength) {
      return description;
    } else {
      const truncatedText = description.substring(0, maxLength);
      return truncatedText + "...";
    }
  };

  return (
    <div className="col">
      <div className="card food-card rounded-card h-100 shadow-lg">
        <img
          src={imageUrl + food.item.imagePath}
          className="card-img-top rounded"
          alt="img"
          style={{
            maxHeight: "300px", // Adjust the maximum height as needed
            margin: "0 auto",
          }}
        />

        <div className="card-body text-color">
          <h5 className="card-title d-flex justify-content-between text-color-second">
            <div>
              <b>{food.item.itemName}</b>
            </div>
          </h5>
          <p className="card-text">
            <b>{descriptionToShow(food.item.description, 50)}</b>
          </p>
        </div>
        <div className="card-footer">
          <div className="d-flex justify-content-between mt-2">
            <ItemCounter item={food.item} key={food.item.id} />
            <div className="text-color-second">
              <p>
                <span>
                  <span>Price : &#8377;{food.item.price}</span>
                </span>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FoodCard;

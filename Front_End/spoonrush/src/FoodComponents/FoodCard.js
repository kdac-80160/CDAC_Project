
import ItemCounter from "../Styles/ItemCounter";


const FoodCard = (food) => {
  console.log(food.item.itemName);
  const descriptionToShow = (description, maxLength) => {
    // console.log(description.length);
    if (description.length <= maxLength) {
      return description;
    } else {
      const truncatedText = description.substring(0, maxLength);
      return truncatedText + "...";
    }
  };

  return (
   
    <div className="col">
      <div class="card food-card rounded-card h-100 shadow-lg">
        <img
          src={food.item.imagePath}
          class="card-img-top rounded"
          alt="img"
          style={{
            maxHeight: "300px", // Adjust the maximum height as needed
            margin: "0 auto",
          }}
        />

        <div class="card-body text-color">
          <h5 class="card-title d-flex justify-content-between text-color-second">
            <div>
              <b>{food.item.itemName}</b>
            </div>
          </h5>
          <p className="card-text">
            <b>{descriptionToShow(food.item.discription, 50)}</b>
          </p>
        </div>
        <div class="card-footer">
          <div className="d-flex justify-content-between mt-2">
             <ItemCounter item ={food.item} key={food.item.id}/>
            <div className="text-color-second">
              <p>
                <span>
                  <h4>Price : &#8377;{food.item.price}</h4>
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
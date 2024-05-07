var myObject = JSON.parse(localStorage.getItem('offer'));

function showData() {
	let table = "";
	// loop through the array
	for (let i = 0; i < myObject.length; i++) {
		table += `
        <div class="col-lg-3" id="delete">
        <div class="card mb-5 mb-lg-0">
          <div class="card-body">
            <h5
              class="card-title text-muted text-uppercase text-center"
            >
              ${myObject[i].name}
            </h5>
            <h6 class="card-price text-center mt-3">
              $${myObject[i].price}<span class="period"></span>
            </h6>
            <hr />
            <ul class="fa-ul">
              <li>
                <span class="fa-li"
                  ><i class="fas fa-check"></i></span
                >Free Breakfast
              </li>
              <li>
                <span class="fa-li"
                  ><i class="fas fa-check"></i></span
                >Free Wifi
              </li>
              <li>
                <span class="fa-li"
                  ><i class="fas fa-check"></i></span
                >Air Conditioning
              </li>
              <li>
                <span class="fa-li"
                  ><i class="fas fa-check"></i></span
                >Laundry
              </li>
              <li>
                <span class="fa-li"
                  ><i class="fas fa-check"></i></span
                >Parking
              </li>
            </ul>
            <button
              href=""
              class="btn btn-block btn-primary text-uppercase"
              id="delete"
              onclick="delete_offer(${i})"
            >
              Delete
            </button>
          </div>
        </div>
      </div>
                `;
	}
	// display variable table in table html
	document.getElementById("card_offer").innerHTML = table;
}
showData();

function delete_offer(i) {
    myObject.splice(i, 1);
	localStorage.offer = JSON.stringify(myObject);
	showData();
}
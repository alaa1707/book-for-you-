
// ################################################################################################################################
// ################################################################################################################################
// ########################################               ADD_OFFER            ####################################################
// ################################################################################################################################
// ################################################################################################################################



// get data from page html
let room_name = document.getElementById("Name");
let room_RoomType = document.getElementById("RoomType");
let book_discount = document.getElementById("discount");
let autohr = document.getElementById("Author");

// let staff_role = document.getElementById("Role");
let save_room = document.getElementById("save_room");



// ###########################################################################
const elementSuccess = document.getElementById("messageSuccess");
const elementError = document.getElementById("messageError");
const elementUpdate = document.getElementById("messageUpdate");
// ###########################################################################


// local storage
let data_offer;
if (localStorage.room != null) {
	data_offer = JSON.parse(localStorage.offer);
} else {
	// initialize empty array
	data_offer = [];
}

// create product
save_room.onclick = function () 
{
	// make list with key and value to store it in local storage
	let new_offer = {
		name: room_name.value.toLowerCase(),
		roomType: room_RoomType.value,
		Author: autohr.value,
        book_discount: book_discount.value,

	};

	// check if the user send empty values to store it or no
	if (room_name.value != "" && room_RoomType.value != "" && book_discount.value != ""  && autohr.value != "" )
		{
		// if (mood === "create") {
			data_offer.push(new_offer);
			// display message success
			elementSuccess.style.display = "block";
			setTimeout(() => {
				elementSuccess.style.display = "none";
			}, 2000);
			// clear cells
			room_name.value = "";
			room_RoomType.value = "";
            autohr.value="";
			discount.value="";
			// room_.value = "";
		} 
		else 
		{
		// display message error
			elementError.style.display = "block";
			setTimeout(() => {
				elementError.style.display = "none";
			}, 3000);
		}
	// save to local storage
	localStorage.setItem("offer", JSON.stringify(data_offer));
	console.log(localStorage.getItem('offer'));
    // console.log(localStorage.staff);
};


function selectOptions() {
	// var list = ["Active", "Inactive"];
	// var option = "";
	// for (i = 0; i < list.length; i++) {
	// 	option += '<option value = "' + list[i] + '">' + list[i] + "</option>";
	// }
	// document.getElementById("Status").innerHTML = option;

    var list_1 = ["Cooking", "Health", "Science Fiction", "Historical","European Literature","Programming"];
	var option_1 = "";
	for (i = 0; i < list_1.length; i++) {
		option_1 += '<option value = "' + list_1[i] + '">' + list_1[i] + "</option>";
	}
	document.getElementById("RoomType").innerHTML = option_1;
}
selectOptions();
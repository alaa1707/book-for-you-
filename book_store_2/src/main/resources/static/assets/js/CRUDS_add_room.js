
// ################################################################################################################################
// ################################################################################################################################
// ########################################               ADD_STAFF            ####################################################
// ################################################################################################################################
// ################################################################################################################################



// get data from page html
let room_name = document.getElementById("Name");
let room_RoomType = document.getElementById("RoomType");

let room_Status = document.getElementById("Status");
// let staff_role = document.getElementById("Role");
let save_room = document.getElementById("save_room");



// ###########################################################################
const elementSuccess = document.getElementById("messageSuccess");
const elementError = document.getElementById("messageError");
const elementUpdate = document.getElementById("messageUpdate");
// ###########################################################################


// local storage
let data_room;
if (localStorage.room != null) {
	data_room = JSON.parse(localStorage.room);
} else {
	// initialize empty array
	data_room = [];
}

// create product
save_room.onclick = function () 
{
	// make list with key and value to store it in local storage
	let new_room = {
		name: room_name.value.toLowerCase(),
		roomType: room_RoomType.value,
		Status: room_Status.value,
	};

	// check if the user send empty values to store it or no
	if (room_name.value != "" && room_RoomType.value != "" && room_Status.value != "")
		{
		// if (mood === "create") {
			data_room.push(new_room);
			// display message success
			elementSuccess.style.display = "block";
			setTimeout(() => {
				elementSuccess.style.display = "none";
			}, 2000);
			// clear cells
			room_name.value = "";
			room_RoomType.value = "";
			room_Status.value = "";
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
	localStorage.setItem("room", JSON.stringify(data_room));
	console.log(localStorage.getItem('room'));
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
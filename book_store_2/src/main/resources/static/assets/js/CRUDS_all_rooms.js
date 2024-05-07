// ################################################################################################################################
// ################################################################################################################################
// ########################################               ALL_STAFF            ####################################################
// ################################################################################################################################
// ################################################################################################################################
let tmp;
let mood = "create";
let room_name = document.getElementById("Name");
let room_RoomType = document.getElementById("RoomType");
let room_Status = document.getElementById("Status");
// let staff_role = document.getElementById("Role");
let save_room = document.getElementById("save_room");

const elementSuccess = document.getElementById("messageSuccess");
let edit_form = document.getElementById("form-edit");

var myObject = JSON.parse(localStorage.getItem("room"));

// Read data form local storage and display it in table html
function showData() {
	let table = "";
	// loop through the array
	for (let i = 0; i < myObject.length; i++) {
		table += `
            <tr>
                <td>ST-${i + 1}</td>
                <td>${myObject[i].name}</td>
                <td>${myObject[i].roomType}</td>
                <td>${myObject[i].Status}</td>
                <td><button onclick="updateData(${i})" type="button" class="btn btn-rounded btn-outline-success">Edit</button></td>
				<td><button onclick="deleteData(${i})" type="button" class="btn btn-rounded btn-outline-danger"> Delete</button></td>

            </tr>
                `;
	}
	document.getElementById("tbody").innerHTML = table;
}
showData();

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
		option_1 +=
			'<option value = "' + list_1[i] + '">' + list_1[i] + "</option>";
	}
	document.getElementById("RoomType").innerHTML = option_1;
}
selectOptions();

// delete
function deleteData(i) {
	myObject.splice(i, 1);
	localStorage.room = JSON.stringify(myObject);
	showData();
}

let data_room;
if (localStorage.room != null) {
	data_room = JSON.parse(localStorage.room);
} else {
	// initialize empty array
	data_room = [];
}

// update
function updateData(i) {
	edit_form.style.display = "block";
	room_name.value = myObject[i].name;
	room_RoomType.value = myObject[i].roomType;
	room_Status.value = myObject[i].Status;
	// room_.value = myObject[i].role;

	tmp = i;
	scroll({ top: 0, behavior: "smooth" });
}

save_room.onclick = function () {
	let room_name = document.getElementById("Name");
	let room_RoomType = document.getElementById("RoomType");
	let room_Status = document.getElementById("Status");
	// make list with key and value to store it in local storage
	let new_room = {
		name: room_name.value.toLowerCase(),
		roomType: room_RoomType.value,
		Status: room_Status.value,
	};
	data_room[tmp] = new_room;
	edit_form.style.display = "block";
	window.location.reload();
	localStorage.setItem("room", JSON.stringify(data_room));
	alert("Your staff data has been updated successfully");
	// showData();
};

// search
function searchData(value) {
	let table = "";
	for (let i = 0; i < myObject.length; i++) {
		if (
			myObject[i].roomType.includes(
				value.charAt(0).toUpperCase() + value.slice(1)
			)
		) {
			table += `
                    <tr>
						<td>ST-${i + 1}</td>
						<td>${myObject[i].name}</td>
                        <td>${myObject[i].roomType}</td>
                        <td>${myObject[i].Status}</td>
                        <td><button onclick="updateData(${i})" type="button" class="btn btn-rounded btn-outline-success">Edit</button></td>
                        <td><button onclick="deleteData(${i})" type="button" class="btn btn-rounded btn-outline-danger"> Delete</button></td>
                    </tr>
                `;
		}
	}
	// show content in localStorage in table html
	document.getElementById("tbody").innerHTML = table;
}

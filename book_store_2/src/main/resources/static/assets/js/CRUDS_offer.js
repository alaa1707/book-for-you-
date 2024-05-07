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
let discount = document.getElementById("discount");
// let staff_role = document.getElementById("Role");
let save_room = document.getElementById("save_room");

const elementSuccess = document.getElementById("messageSuccess");

var myObject = JSON.parse(localStorage.getItem("offer"));

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
                <td>${myObject[i].Author}</td>
                <td>${myObject[i].book_discount}</td>
				<td><button onclick="deleteData(${i})" type="button" class="btn btn-rounded btn-outline-danger"> Delete</button></td>

            </tr>
                `;
	}
	document.getElementById("tbody").innerHTML = table;
}
showData();



// delete
function deleteData(i) {
	myObject.splice(i, 1);
	localStorage.offer = JSON.stringify(myObject);
	showData();
}

let data_room;
if (localStorage.room != null) {
	data_room = JSON.parse(localStorage.room);
} else {
	// initialize empty array
	data_room = [];
}



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
                        <td>${myObject[i].discount}</td>
                        <td><button onclick="deleteData(${i})" type="button" class="btn btn-rounded btn-outline-danger"> Delete</button></td>
                    </tr>
                `;
		}
	}
	// show content in localStorage in table html
	document.getElementById("tbody").innerHTML = table;
}

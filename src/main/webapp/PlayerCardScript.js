document.addEventListener("DOMContentLoaded", function () {
    renderGrid();  // Load player list on page load

    // Ensure "Add Player" button exists before adding event listener
    const addPlayerBtn = document.getElementById("addPlayerBtn");
    if (addPlayerBtn) {
        addPlayerBtn.addEventListener("click", () => {
            document.getElementById("playerFormContainer").style.display = "block";
        });
    }

    // Close edit form when clicking cancel
    const cancelEdit = document.getElementById("cancelEdit");
    if (cancelEdit) {
        cancelEdit.addEventListener("click", () => {
            document.getElementById("playerEditForm").style.display = "none";
        });
    }

    // Close add player form when clicking cancel
    const cancelAdd = document.getElementById("cancelAdd");
    if (cancelAdd) {
        cancelAdd.addEventListener("click", () => {
            document.getElementById("playerFormContainer").style.display = "none";
        });
    }
});

// **Fetch and Display Players**
const renderGrid = () => {
    fetch("FetchPlayersServlet")
        .then(response => response.json())
        .then(players => {
            const playerGrid = document.getElementById("playerGrid");
            playerGrid.innerHTML = ""; // Clear the grid before rendering

            players.forEach(player => {
                const card = document.createElement("div");
                card.classList.add("player-card");

                card.innerHTML = `
                    <img src="${player.image}" alt="${player.name}">
                    <h2>${player.name}</h2>
                    <p>Score: ${player.score}</p>
                    <p>Bulls Tamed: ${player.bullsTamed}</p>
                    
                    <!-- Hidden Details (Initially hidden) -->
                    <div class="hidden-details">
                        <p>Age: ${player.age}</p>
                        <p>Region: ${player.region}</p>
                        <p>Nationality : ${player.nationality}</p>
                        <p>Matches Played: ${player.matches}</p>
                    </div>
                    
                    <button class="edit-btn" onclick="editPlayer('${player.id}', '${player.name}', '${player.image}', '${player.score}', '${player.bullsTamed}', '${player.age}', '${player.region}', '${player.nationality}', '${player.matches}')">Edit</button>
                `;

                playerGrid.appendChild(card);
            });
        })
        .catch(error => console.error("Error fetching players:", error));
};

// Open the Edit Form and Populate Data
// Open the Edit Form and Populate Data
const editPlayer = (id, name, image, score, bullsTamed, age, region, nationality, matches) => {
    console.log("🛠 Editing Player - ID:", id); // Debugging output

    // Ensure the ID is correctly set before displaying the form
    const idElement = document.getElementById("editPlayerId");
    if (!idElement) {
        console.error("❌ Error: ID input field not found!");
        return;
    }
    idElement.value = id;

    document.getElementById("editPlayerName").value = name;
    document.getElementById("editPlayerImage").value = image;
    document.getElementById("editPlayerScore").value = score;
    document.getElementById("editPlayerBullsTamed").value = bullsTamed;
    document.getElementById("editPlayerAge").value = age;
    document.getElementById("editPlayerRegion").value = region;
    document.getElementById("editPlayerNationality").value = nationality;
    document.getElementById("editPlayerMatches").value = matches;

    // Show the edit form
    document.getElementById("editFormContainer").style.display = "block";
};


// Submit Edited Player Details
const submitEditPlayer = () => {
    const idElement = document.getElementById("editPlayerId"); // Get ID element
    if (!idElement || !idElement.value.trim()) {
        console.error("❌ Error: Player ID is missing or not found!");
        alert("Error: Player ID is missing.");
        return;
    }

    const id = idElement.value.trim();  // Ensure ID is assigned correctly
    console.log("✅ Submitting Edit - Player ID:", id); // Debugging

    const formData = new FormData();
    formData.append("id", id);
    formData.append("name", document.getElementById("editPlayerName").value.trim());
    formData.append("image", document.getElementById("editPlayerImage").value.trim());
    formData.append("score", document.getElementById("editPlayerScore").value.trim());
    formData.append("bullsTamed", document.getElementById("editPlayerBullsTamed").value.trim());
    formData.append("age", document.getElementById("editPlayerAge").value.trim());
    formData.append("region", document.getElementById("editPlayerRegion").value.trim());
    formData.append("nationality", document.getElementById("editPlayerNationality").value.trim());
    formData.append("matches", document.getElementById("editPlayerMatches").value.trim());

    fetch("UpdatePlayerServlet", {
        method: "POST",
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            if (data.trim() === "Success") {
                alert("✅ Player updated successfully!");
                document.getElementById("editFormContainer").style.display = "none";
                renderGrid(); // Refresh the player list
            } else {
                console.error("❌ Server Response:", data);
                alert("Error: " + data);
            }
        })
        .catch(error => console.error("❌ Error updating player:", error));
};


// Show "Add Player" Form
document.getElementById("addPlayerBtn").addEventListener("click", () => {
    document.getElementById("playerFormContainer").style.display = "block";
});

// **Handle Adding a New Player**
document.getElementById("addPlayerForm").addEventListener("submit", (event) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append("name", document.getElementById("playerNameInput").value.trim());
    formData.append("image", document.getElementById("playerImageInput").value.trim());
    formData.append("score", document.getElementById("playerScoreInput").value.trim());
    formData.append("bullsTamed", document.getElementById("playerBullsTamedInput").value.trim());
    formData.append("age", document.getElementById("playerAgeInput").value.trim());
    formData.append("region", document.getElementById("playerRegionInput").value.trim());
    formData.append("nationality",  document.getElementById("playerNationalityInput").value.trim());
    formData.append("matches", document.getElementById("playerMatchesInput").value.trim());

    fetch("PlayerServlet", {
        method: "POST",
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            if (data.trim() === "Success") {
                alert("Player added successfully!");
                document.getElementById("addPlayerForm").reset();
                document.getElementById("playerFormContainer").style.display = "none";
                renderGrid(); // Reload player list
            } else {
                console.error("Server Response:", data);
                alert("Error: " + data);
            }
        })
        .catch(error => console.error("Error adding player:", error));
});
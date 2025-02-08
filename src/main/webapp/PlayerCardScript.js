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
                        <p>Matches Played: ${player.matches}</p>
                    </div>
                    
                    <button class="edit-btn" onclick="editPlayer('${player.id}', '${player.name}', '${player.image}', '${player.score}', '${player.bullsTamed}', '${player.age}', '${player.region}', '${player.matches}')">Edit</button>
                `;

                playerGrid.appendChild(card);
            });
        })
        .catch(error => console.error("Error fetching players:", error));
};

// **Open the Edit Form and Populate Data**
const editPlayer = (id, name, image, score, bullsTamed, age, region, matches) => {
    document.getElementById("playerEditForm").style.display = "block";  // Show form
    document.getElementById("playerIdInput").value = id;
    document.getElementById("playerNameInput").value = name;
    document.getElementById("playerImageInput").value = image;
    document.getElementById("playerScoreInput").value = score;
    document.getElementById("playerBullsTamedInput").value = bullsTamed;
    document.getElementById("playerAgeInput").value = age;
    document.getElementById("playerRegionInput").value = region;
    document.getElementById("playerMatchesInput").value = matches;
};

// **Submit Edited Player Details**
const submitEditPlayer = () => {
    const id = document.getElementById("playerIdInput").value.trim();
    if (!id) {
        alert("Error: Player ID is missing!");
        return;
    }

    const formData = new FormData();
    formData.append("id", id);
    formData.append("name", document.getElementById("playerNameInput").value.trim());
    formData.append("image", document.getElementById("playerImageInput").value.trim());
    formData.append("score", document.getElementById("playerScoreInput").value.trim());
    formData.append("bullsTamed", document.getElementById("playerBullsTamedInput").value.trim());
    formData.append("age", document.getElementById("playerAgeInput").value.trim());
    formData.append("region", document.getElementById("playerRegionInput").value.trim());
    formData.append("matches", document.getElementById("playerMatchesInput").value.trim());

    fetch("UpdatePlayerServlet", {
        method: "POST",
        body: formData
    })
        .then(response => response.text())
        .then(data => {
            if (data.trim() === "Success") {
                alert("Player updated successfully!");
                document.getElementById("playerEditForm").reset();
                document.getElementById("playerEditForm").style.display = "none";
                renderGrid(); // Reload player list
            } else {
                console.error("Server Response:", data);
                alert("Error: " + data);
            }
        })
        .catch(error => console.error("Error updating player:", error));
};

// **Show "Add Player" Form**
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

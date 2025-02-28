// Function to Fetch Players Based on Region Input
async function fetchPlayers() {
    let region = document.getElementById("region").value.trim();
    let errorMessage = document.getElementById("error-message");

    if (region === "") {
        errorMessage.textContent = "Please enter a region.";
        errorMessage.style.display = "block";
        return;
    }

    try {
        let response = await fetch(`http://localhost:8080/VaadiVaasal_webapp_war_exploded/PlayersForScore?region=${encodeURIComponent(region)}`);

        if (!response.ok) {
            throw new Error(`Failed to fetch players. Status: ${response.status}`);
        }

        const data = await response.json();

        if (!Array.isArray(data)) {
            throw new Error("Invalid server response format.");
        }

        let tableBody = document.getElementById("scoreboard-body");
        tableBody.innerHTML = ""; // Clear previous data
        errorMessage.style.display = "none";

        data.forEach((player, index) => {
            let row = document.createElement("tr");
            row.innerHTML = `
                <td>${index + 1}</td>
                <td>${player.name}</td>
                <td><input type="number" id="bulls-${index}" value="0" min="0" oninput="updateScore(${index})"></td>
                <td><input type="number" id="time-${index}" value="0" min="0" oninput="updateScore(${index})"></td>
                <td><input type="number" id="fouls-${index}" value="0" min="0" oninput="updateScore(${index})"></td>
                <td id="score-${index}">0</td>
            `;
            tableBody.appendChild(row);
        });
    } catch (error) {
        console.error("Error fetching players:", error);
        errorMessage.textContent = `Failed to connect to server: ${error.message}`;
        errorMessage.style.display = "block";
    }
}

// Function to Calculate Score
function calculateScore(bullsTamed, timeTaken, fouls) {
    let score = (bullsTamed * 5) + (Math.floor(timeTaken / 10) * 10) - (fouls * 5);
    return Math.max(score, 0); // Ensure the score doesn't go negative
}

// Function to Update Scores Dynamically
function updateScore(index) {
    let bullsTamed = parseInt(document.getElementById(`bulls-${index}`).value) || 0;
    let timeTaken = parseInt(document.getElementById(`time-${index}`).value) || 0;
    let fouls = parseInt(document.getElementById(`fouls-${index}`).value) || 0;

    let totalScore = calculateScore(bullsTamed, timeTaken, fouls);
    document.getElementById(`score-${index}`).innerText = totalScore;
}

// Function to Sort Table by Total Score
function sortTable() {
    let rows = Array.from(document.querySelectorAll("#scoreboard-body tr"));

    rows.sort((a, b) => {
        let scoreA = parseInt(a.cells[5].textContent) || 0;
        let scoreB = parseInt(b.cells[5].textContent) || 0;
        return scoreB - scoreA; // Sort in descending order
    });

    let tableBody = document.getElementById("scoreboard-body");
    tableBody.innerHTML = "";
    rows.forEach(row => tableBody.appendChild(row));
}

// Function to Reset Scores
function resetScores() {
    document.querySelectorAll("#scoreboard-body input").forEach(input => {
        input.value = 0;
    });
    document.querySelectorAll("#scoreboard-body td[id^='score-']").forEach(td => {
        td.textContent = 0;
    });
}

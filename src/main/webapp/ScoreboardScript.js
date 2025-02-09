// Sample Jallikattu Match Data
const matches = [
    { date: "Sun, 9 Feb 2025", team1: "Team A", team2: "Team B", score: "5 bulls tamed", status: "Live", img1: "team1.png", img2: "team2.png" },
    { date: "Sun, 9 Feb 2025", team1: "Team C", team2: "Team D", score: "Match Abandoned", status: "Abandoned", img1: "team3.png", img2: "team4.png" },
    { date: "Mon, 10 Feb 2025", team1: "Team E", team2: "Team F", score: "8 bulls tamed", status: "Completed", img1: "team5.png", img2: "team6.png" },
];

const fixturesList = document.getElementById("fixturesList");

// Function to Load Matches
function loadMatches() {
    fixturesList.innerHTML = ""; // Clear existing data

    matches.forEach(match => {
        const matchCard = document.createElement("div");
        matchCard.classList.add("match-card");

        matchCard.innerHTML = `
            <img src="${match.img1}" alt="${match.team1}">
            <div class="match-info">
                <strong>${match.team1} vs ${match.team2}</strong>
                <p>${match.date} | ${match.score}</p>
                <p class="match-status ${match.status.toLowerCase()}">${match.status}</p>
            </div>
            <img src="${match.img2}" alt="${match.team2}">
        `;

        fixturesList.appendChild(matchCard);
    });
}

// Load Matches on Page Load
document.addEventListener("DOMContentLoaded", loadMatches);

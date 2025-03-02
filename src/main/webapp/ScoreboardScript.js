document.addEventListener("DOMContentLoaded", function () {
    const matchContainer = document.getElementById("matchContainer");
    const addMatchForm = document.getElementById("addMatchForm");

    if (!matchContainer || !addMatchForm) {
        console.error("Error: matchContainer or addMatchForm not found in the HTML.");
        return;
    }

    let matches = [
        {
            venue: "Madurai",
            teams: "Team A vs Team B",
            status: "Live",
            date: "2025-03-10",
            youtubeLink: "https://www.youtube.com/watch?v=example1"
        },
        {
            venue: "Trichy",
            teams: "Team C vs Team D",
            status: "Upcoming",
            date: "2025-04-15",
            youtubeLink: "https://www.youtube.com/watch?v=example2"
        },
        {
            venue: "Pudukkottai",
            teams: "Team E vs Team F",
            status: "Completed",
            date: "2025-02-28",
            youtubeLink: "https://www.youtube.com/watch?v=example3"
        }
    ];

    function renderMatches() {
        matchContainer.innerHTML = "";
        matches.forEach(match => {
            const matchCard = document.createElement("div");
            matchCard.classList.add("match-card", match.status.toLowerCase());

            matchCard.innerHTML = `
                <h3>${match.venue}</h3>
                <p><strong>Teams:</strong> ${match.teams}</p>
                <p><strong>Status:</strong> <span class="${match.status.toLowerCase()}">${match.status}</span></p>
                <p><strong>Date:</strong> ${match.date}</p>
                <a href="${match.youtubeLink}" target="_blank">📺 Watch on YouTube</a>
            `;
            matchContainer.appendChild(matchCard);
        });
    }

    addMatchForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const venue = document.getElementById("venue");
        const teams = document.getElementById("teams");
        const status = document.getElementById("status");
        const date = document.getElementById("date");
        const youtubeLink = document.getElementById("youtubeLink");

        if (!venue || !teams || !status || !date || !youtubeLink) {
            console.error("Error: One or more input fields are missing.");
            return;
        }

        const newMatch = {
            venue: venue.value,
            teams: teams.value,
            status: status.value,
            date: date.value,
            youtubeLink: youtubeLink.value
        };

        matches.push(newMatch);
        renderMatches();

        addMatchForm.reset();
    });

    themeToggle.addEventListener("click", () => {
        document.body.classList.toggle("dark-mode");
        themeToggle.textContent = document.body.classList.contains("dark-mode") ? "☀️ Light Mode" : "🌙 Dark Mode";
    });

    renderMatches();
});

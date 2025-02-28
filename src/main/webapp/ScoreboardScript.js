document.addEventListener("DOMContentLoaded", () => {
    const matches = [
        { team: "Team A", format: "Traditional", series: "Championship", date: "March 10, 2025" },
        { team: "Team B", format: "Modern", series: "Regional", date: "March 15, 2025" },
        { team: "Team A", format: "Traditional", series: "Regional", date: "March 20, 2025" }
    ];

    const matchContainer = document.getElementById("matches");
    const searchInput = document.getElementById("search");
    const teamFilter = document.getElementById("team");
    const formatFilter = document.getElementById("format");
    const seriesFilter = document.getElementById("series");
    const themeToggle = document.getElementById("theme-toggle");

    function displayMatches() {
        matchContainer.innerHTML = "";
        const filteredMatches = matches.filter(match => {
            return (teamFilter.value === "" || match.team === teamFilter.value) &&
                (formatFilter.value === "" || match.format === formatFilter.value) &&
                (seriesFilter.value === "" || match.series === seriesFilter.value) &&
                (searchInput.value === "" || match.team.toLowerCase().includes(searchInput.value.toLowerCase()));
        });

        if (filteredMatches.length === 0) {
            matchContainer.innerHTML = "<p>No upcoming matches.</p>";
            return;
        }

        filteredMatches.forEach(match => {
            const matchCard = document.createElement("div");
            matchCard.classList.add("match-card");
            matchCard.innerHTML = `<h3>${match.team} - ${match.format}</h3><p>${match.series} - ${match.date}</p>`;
            matchContainer.appendChild(matchCard);
        });
    }

    searchInput.addEventListener("input", displayMatches);
    teamFilter.addEventListener("change", displayMatches);
    formatFilter.addEventListener("change", displayMatches);
    seriesFilter.addEventListener("change", displayMatches);

    // Dark Mode Toggle
    themeToggle.addEventListener("click", () => {
        document.body.classList.toggle("dark-mode");
        themeToggle.textContent = document.body.classList.contains("dark-mode") ? "☀️ Light Mode" : "🌙 Dark Mode";
    });

    displayMatches();
});

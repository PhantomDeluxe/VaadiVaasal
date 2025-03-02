document.addEventListener("DOMContentLoaded", function () {
    const addTamerBullBtn = document.getElementById("addTamerBullBtn");
    const formContainer = document.getElementById("tamerBullFormContainer");
    const form = document.getElementById("addTamerBullForm");
    const tamerBullGrid = document.getElementById("tamerBullGrid");
    const editFormContainer = document.getElementById("editFormContainer");
    const editForm = document.getElementById("editTamerBullForm");
    const cancelAddBtn = document.getElementById("cancelAddBtn");
    const cancelEditBtn = document.getElementById("cancelEditBtn");

    let tamersAndBulls = [];
    let editingTamerId = null;

    addTamerBullBtn.addEventListener("click", function () {
        formContainer.style.display = "block";
    });

    window.addEventListener("click", function (event) {
        if (event.target === formContainer) formContainer.style.display = "none";
        if (event.target === editFormContainer) editFormContainer.style.display = "none";
    });

    cancelAddBtn.addEventListener("click", function () {
        formContainer.style.display = "none";
        form.reset();
    });

    cancelEditBtn.addEventListener("click", function () {
        editFormContainer.style.display = "none";
        editForm.reset();
    });

    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent form from refreshing the page

        const tamerData = {
            name: document.getElementById("tamerName").value,
            experience: document.getElementById("tamerExperience").value,
            region: document.getElementById("tamerRegion").value,
            nationality: document.getElementById("tamerNationality").value,
            matchesPlayed: document.getElementById("matchesPlayed").value,
            bullName: document.getElementById("bullName").value,
            bullStrength: document.getElementById("bullStrength").value,
            bullSpeed: document.getElementById("bullSpeed").value,
            bullBreed: document.getElementById("bullBreed").value
        };

        // Add the new tamer & bull to the array
        tamersAndBulls.push(tamerData);

        // Refresh the display
        renderTamerBullCards();

        // Send data to the servlet using fetch API
        let formData = new FormData(form);
        fetch("BullsCardServlet", {
            method: "POST",
            body: formData
        })
            .then(response => response.text())
            .then(data => {
                alert("Tamer & Bull added successfully!");
                console.log(data);
            })
            .catch(error => {
                console.error("Error:", error);
                alert("Failed to add Tamer & Bull.");
            });

        // Hide the form and reset it
        formContainer.style.display = "none";
        form.reset();
    });

    function renderTamerBullCards() {
        tamerBullGrid.innerHTML = ""; // Clear previous cards
        tamersAndBulls.forEach(tamer => {
            const card = document.createElement("div");
            card.classList.add("tamer-bull-card");
            card.innerHTML = `
                <h3>${tamer.name} (Tamer)</h3>
                <p>Experience: ${tamer.experience} Years</p>
                <p>Region: ${tamer.region}</p>
                <p>Matches: ${tamer.matchesPlayed}</p>
                <h3>${tamer.bullName} (Bull)</h3>
                <p>Strength: ${tamer.bullStrength}</p>
                <p>Speed: ${tamer.bullSpeed}</p>
                <p>Breed: ${tamer.bullBreed}</p>
            `;
            tamerBullGrid.appendChild(card);
        });
    }
});

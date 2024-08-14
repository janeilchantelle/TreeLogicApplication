// input-form.js

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("number-form");
    const treeResultContainer = document.getElementById("tree-result-container");

    form.addEventListener("submit", async (event) => {
        event.preventDefault(); // Prevent form from submitting normally

        const numbersInput = document.getElementById("numbers");
        const numbers = numbersInput.value.trim();

        try {
            const response = await fetch("/process-numbers", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
                body: new URLSearchParams({ numbers: numbers }),
            });

            if (!response.ok) {
                throw new Error("Network response was not ok.");
            }

            const treeJson = await response.json();
            displayTree(treeJson);
        } catch (error) {
            treeResultContainer.innerHTML = `<p class="error-message">Error: ${error.message}</p>`;
        }
    });

    function displayTree(treeJson) {
        // Clear previous result
        treeResultContainer.innerHTML = "";

        // Create a new container for the tree result
        const treeContainer = document.createElement("div");
        treeContainer.className = "tree-result";

        // Add the tree JSON to the container
        const pre = document.createElement("pre");
        pre.textContent = JSON.stringify(treeJson, null, 2);
        treeContainer.appendChild(pre);

        // Add the container to the result section
        treeResultContainer.appendChild(treeContainer);
    }
});

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("number-form");
    const treeResultContainer = document.getElementById("tree-result-container");

    form.addEventListener("submit", async (event) => {
        event.preventDefault();

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
        treeResultContainer.innerHTML = "";

        const treeContainer = document.createElement("div");
        treeContainer.className = "tree-result";

        const pre = document.createElement("pre");
        pre.textContent = JSON.stringify(treeJson, null, 2);
        treeContainer.appendChild(pre);

        treeResultContainer.appendChild(treeContainer);
    }
});

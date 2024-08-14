document.addEventListener("DOMContentLoaded", async () => {
    const treeListContainer = document.getElementById("tree-list");
    const loadingMessage = document.getElementById("loading-message");
    const errorMessage = document.getElementById("error-message");

    loadingMessage.style.display = "block";

    try {
        const response = await fetch("/previous-trees");
        if (!response.ok) {
            throw new Error("Network response was not ok.");
        }

        const treeRecords = await response.json();
        displayTreeRecords(treeRecords);
    } catch (error) {
        errorMessage.textContent = `Error: ${error.message}`;
    } finally {
        loadingMessage.style.display = "none";
    }
});

function displayTreeRecords(treeRecords) {
    const treeListContainer = document.getElementById("tree-list");
    treeListContainer.innerHTML = ""; // Clear existing content

    if (treeRecords.length === 0) {
        treeListContainer.innerHTML = "<li>No previous trees found.</li>";
        return;
    }

    treeRecords.forEach(tree => {
        const listItem = document.createElement("li");
        listItem.textContent = `Tree ID: ${tree.id}`;
        listItem.addEventListener("click", () => fetchAndDisplayTree(tree.id));
        treeListContainer.appendChild(listItem);
    });
}

async function fetchAndDisplayTree(treeId) {
    try {
        const response = await fetch(`/get-tree?id=${treeId}`);
        if (!response.ok) {
            throw new Error("Error fetching data.");
        }

        const treeJson = await response.json();
        displayTreePopup(treeJson);
    } catch (error) {
        alert(`Error fetching tree data: ${error.message}`);
    }
}

function displayTreePopup(treeJson) {
    const popup = document.createElement("div");
    popup.className = "popup";
    popup.innerHTML = `<pre>${JSON.stringify(treeJson, null, 2)}</pre>`;

    const closeButton = document.createElement("button");
    closeButton.textContent = "Close";
    closeButton.addEventListener("click", () => popup.remove());

    popup.appendChild(closeButton);
    document.body.appendChild(popup);
}

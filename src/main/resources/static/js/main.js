// Post Functions
function createPost() {
    const content = document.getElementById('postContent').value;
    const visibility = document.getElementById('postVisibility').value;

    if (!content.trim()) {
        alert('Please enter some content');
        return;
    }

    fetch('/post/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `content=${encodeURIComponent(content)}&visibility=${visibility}`
    })
        .then(response => response.text())
        .then(result => {
            if (result === 'success') {
                location.reload();
            } else {
                alert('Error creating post');
            }
        });
}

function deletePost(postId) {
    if (!confirm('Are you sure you want to delete this post?')) {
        return;
    }

    fetch(`/post/${postId}`, {
        method: 'DELETE'
    })
        .then(response => response.text())
        .then(result => {
            if (result === 'success') {
                location.reload();
            } else {
                alert('Error deleting post');
            }
        });
}

// Bookmark Functions
function toggleBookmark(postId) {
    const isBookmarked = document.querySelector(`[data-post-id="${postId}"] .bookmark-btn`).classList.contains('bookmarked');
    const url = isBookmarked ? '/bookmark/remove' : '/bookmark/add';

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `postId=${postId}`
    })
        .then(response => response.text())
        .then(result => {
            if (result === 'success') {
                const bookmarkBtn = document.querySelector(`[data-post-id="${postId}"] .bookmark-btn`);
                bookmarkBtn.classList.toggle('bookmarked');
            }
        });
}

// Message Functions
function sendMessage() {
    const content = document.getElementById('messageContent').value;
    const receiverId = document.getElementById('receiverId').value;

    if (!content.trim()) {
        return;
    }

    fetch('/message/send', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `receiverId=${receiverId}&content=${encodeURIComponent(content)}`
    })
        .then(response => response.text())
        .then(result => {
            if (result === 'success') {
                location.reload();
            }
        });
}

// Profile Functions
function editProfile() {
    document.getElementById('profileView').style.display = 'none';
    document.getElementById('profileEdit').style.display = 'block';
}

function cancelEdit() {
    document.getElementById('profileView').style.display = 'block';
    document.getElementById('profileEdit').style.display = 'none';
}

// Initialize
document.addEventListener('DOMContentLoaded', function() {
    // Auto-resize textarea
    const textareas = document.querySelectorAll('textarea');
    textareas.forEach(textarea => {
        textarea.addEventListener('input', function() {
            this.style.height = 'auto';
            this.style.height = this.scrollHeight + 'px';
        });
    });

    // Character counter
    const postContent = document.getElementById('postContent');
    if (postContent) {
        postContent.addEventListener('input', function() {
            const count = this.value.length;
            const counter = document.getElementById('charCount');
            if (counter) {
                counter.textContent = `${count}/500`;
            }
        });
    }
});
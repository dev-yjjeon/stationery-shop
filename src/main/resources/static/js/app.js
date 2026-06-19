document.addEventListener('DOMContentLoaded', () => {
    // 1. Theme Toggle Initialization
    const themeToggleBtn = document.getElementById('theme-toggle');
    const currentTheme = localStorage.getItem('theme') || 'dark'; // default to dark theme
    
    document.documentElement.setAttribute('data-theme', currentTheme);
    updateToggleIcon(themeToggleBtn, currentTheme);

    if (themeToggleBtn) {
        themeToggleBtn.addEventListener('click', () => {
            let theme = document.documentElement.getAttribute('data-theme');
            let newTheme = theme === 'dark' ? 'light' : 'dark';
            document.documentElement.setAttribute('data-theme', newTheme);
            localStorage.setItem('theme', newTheme);
            updateToggleIcon(themeToggleBtn, newTheme);
        });
    }
});

function updateToggleIcon(btn, theme) {
    if (!btn) return;
    if (theme === 'light') {
        btn.textContent = '🌙'; // Icon to switch back to Dark mode
    } else {
        btn.textContent = '☀️'; // Icon to switch back to Light mode
    }
}

// 2. Cart AJAX Operations
const Cart = {
    add(productId, quantity) {
        fetch('/api/cart', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ productId: parseInt(productId), quantity: parseInt(quantity) })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert(data.message);
                location.reload();
            } else {
                alert('에러: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error adding to cart:', error);
            alert('장바구니 담기에 실패했습니다.');
        });
    },

    updateQuantity(cartItemId, quantity) {
        if (quantity < 1) {
            alert('수량은 1개 이상이어야 합니다.');
            return;
        }
        fetch('/api/cart/update', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ cartItemId: parseInt(cartItemId), quantity: parseInt(quantity) })
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                location.reload();
            } else {
                alert('에러: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error updating cart quantity:', error);
            alert('수량 변경에 실패했습니다.');
        });
    },

    remove(cartItemId) {
        if (!confirm('장바구니에서 해당 상품을 삭제하시겠습니까?')) {
            return;
        }
        fetch(`/api/cart/${cartItemId}`, {
            method: 'DELETE'
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                location.reload();
            } else {
                alert('에러: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Error removing cart item:', error);
            alert('상품 삭제에 실패했습니다.');
        });
    }
};

window.Cart = Cart;

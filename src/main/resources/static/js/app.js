document.addEventListener('DOMContentLoaded', () => {
    // 1. Theme Toggle Initialization
    const btnPink = document.getElementById('theme-pink');
    const btnMint = document.getElementById('theme-mint');
    const currentTheme = localStorage.getItem('theme') || 'theme-pink'; // default to theme-pink
    
    document.body.classList.add(currentTheme);

    if (btnPink) {
        btnPink.addEventListener('click', () => {
            document.body.classList.remove('theme-mint');
            document.body.classList.add('theme-pink');
            localStorage.setItem('theme', 'theme-pink');
        });
    }
    if (btnMint) {
        btnMint.addEventListener('click', () => {
            document.body.classList.remove('theme-pink');
            document.body.classList.add('theme-mint');
            localStorage.setItem('theme', 'theme-mint');
        });
    }
});

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

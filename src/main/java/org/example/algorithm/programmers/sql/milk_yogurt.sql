-- 우유와 요거트를 동시에 구입한 장바구니 아이디를 조회
SELECT DISTINCT a.CART_ID FROM CART_PRODUCTS a
JOIN CART_PRODUCTS b
ON a.CART_ID = b.CART_ID
WHERE a.NAME = '우유' AND b.NAME = '요거트';
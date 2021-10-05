Select h.buyer_id, sum(p.price) from house h
inner join price p on p.house_id=h.house_id
group by h.buyer_id
having sum(p.price) > 99 and count(h.buyer_id) > 1

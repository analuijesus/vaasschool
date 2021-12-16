/* os dados de todas as categorias ativas, na ordem */
select * from Category where active = true order by order_visualization;


/* os dados de todas as subcategorias ativas, na ordem */
select * from Subcategory where active = true order by order_visualization;


/* os dados de todos os cursos públicos  */
select * from Course where visibility = 'PUBLIC';


/* os nomes das subcategorias que não tem descrições */
select name from Subcategory where description = '' or description is null;


/* os nomes e ordem das subcategorias ativas e que tem algum curso, na ordem */
select distinct s.name, s.order_visualization 
	   from Subcategory s
	   join Course c on c.subcategory_id = s.id
	   where s.active = true
	   order by s.order_visualization;  
	   

/* o nome e a quantidade de cursos do instrutor que tem mais cursos */

select instructor_name, count(*) as qtd
        from Course
        group by instructor_name
        order by qtd desc
        limit 1;
        

/*os nomes de todas as categorias ativas com a respectiva quantidade de cursos (públicos e privados) e total de horas estimados dos cursos associados 
(sendo 0 se não existir nenhum curso)*/
       
select ca.name , count(c.id) as qtd, coalesce(sum(c.estimated_time_to_finish), 0) as total_de_horas
	    from Category ca
	    left join Subcategory s on s.category_id = ca.id
        left join Course c on c.subcategory_id = s.id
	    where ca.active = true
        group by ca.name;

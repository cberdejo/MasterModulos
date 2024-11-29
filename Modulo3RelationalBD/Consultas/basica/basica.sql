-- 1
select * from sol_2_5;

SELECT 
    P.ID, P.NOMBRE, P.APELLIDO1, P.APELLIDO2, ASI.CODIGO, ASI.NOMBRE ASIGNATURA

FROM
    PROFESORES P
    JOIN IMPARTIR I ON I.PROFESOR = P.ID
    JOIN  ASIGNATURAS ASI ON ASI.CODIGO = I.ASIGNATURA;
-- 2
/*
Obtener el nombre de cada profesor junto con el ID de su director de tesis. En caso de
 no tener director de tesis en el listado debe aparecer “No tiene”
*/


select * from sol_5_2;

SELECT 
    P1.NOMBRE, P1.APELLIDO1, P1.APELLIDO2, NVL(P2.ID,'No tiene') DIRECTOR_TESIS
FROM 
    PROFESORES P1 LEFT JOIN PROFESORES P2 ON (P1.DIRECTOR_TESIS = P2.ID);
-- 3
/*
Nombre y apellidos de parejas de profesores cuya diferencia de antigüedad (en valor
 absoluto) sea inferior a dos años y pertenezcan al mismo departamento. Muestre la
 antigüedad de cada uno de ellos en años. Usar la función MONTHS_BETWEEN
*/

select * from sol_2_8;

SELECT 
    P1.NOMBRE nombrep1, P1.APPELLIDO1 apellido1p1, P1.APELLIDO2 apellido2p1, TRUNC(MONTHS_BETWEEN(SYS_DATE,P1.ANTIGUEDAD)/12) Antiguedad1,
    P2.NOMBRE nombrep2, P2.APPELLIDO1 apellido1p2, P2.APELLIDO2 apellido2p2, TRUNC(MONTHS_BETWEEN(SYS_DATE,P2.ANTIGUEDAD)/12) Antiguedad2
FROM 
    PROFESORES P1, PROFESORES P2
WHERE 
    P1.ID < P2.ID
AND    
    ABS((MONTHS_BETWEEN(SYS_DATE,P1.ANTIGUEDAD)/12) - (MONTHS_BETWEEN(SYS_DATE,P1.ANTIGUEDAD)/12)) < 2
AND
    P1.DEPARTAMENTO = P2.DEPARTAMENTO;
    

-- 4
select * from sol_2_10;
-- 5
select * from sol_2_12;
-- 6
select * from sol_2_14;
-- 7
select * from sol_2_15;
-- 8
select * from sol_2_19;
-- 9
select * from sol_2_23;
-- 10
select * from sol_2_24;

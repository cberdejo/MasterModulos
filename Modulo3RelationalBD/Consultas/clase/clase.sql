------------------------------------------------------------------------------------------------
--------------------------------------LECCIÓN 6-------------------------------------------------
------------------------------------------------------------------------------------------------

/*
1. Nombre y apellidos de los profesores del departamento de Lenguajes.
*/
SELECT P.NOMBRE AS NOMBRE, P.APELLIDO1 AS APELLIDO1, NVL(P.APELLIDO2,' ') AS APELLIDO2
FROM PROFESORES P JOIN DEPARTAMENTOS D ON (P.DEPARTAMENTO=D.CODIGO)
WHERE UPPER(D.NOMBRE) LIKE '%LENGUAJES%'


/*
2. Usando la función NVL extraiga un listado con el código y el nombre de las asignaturas
de las que está matriculado 'Nicolas Bersabe Alba'. Proporcione además el número de
créditos prácticos, pero caso de ser nulo, debe salir "No tiene" en el listado.
*/

SELECT DISTINCT
    ASI.CODIGO, ASI.NOMBRE, NVL(TO_CHAR(ASI.PRACTICOS), 'NO TIENE')
FROM
    ASIGNATURAS ASI
    JOIN MATRICULAR M ON (M.ASIGNATURA=ASI.CODIGO)
    JOIN ALUMNOS A ON (A.DNI = M.ALUMNO)
WHERE 
    UPPER(A.NOMBRE) = 'NICOLAS'
AND 
    UPPER(A.APELLIDO1)='BERSABE'
AND     
    UPPER(A.APELLIDO2)='ALBA';

/*
3. Alumnos que tengan aprobada la asignatura 'Bases de Datos'
*/
SELECT 
    A.*
FROM
    ALUMNOS A
    JOIN MATRICULAR M ON (M.ALUMNO = A.DNI)
    JOIN ASIGNATURAS ASI ON (M.ASIGNATURA = ASI.CODIGO)
WHERE 
    M.CALIFICACION NOT IN ('NP', 'SP') 
AND 
    M.CALIFICACION IS NOT NULL;
/*
4. Nombre y edad de parejas de alumnos que tengan el mismo primer apellido. Téngase
en cuenta que los apellidos podrían estar en mayúscula o en minúscula.
*/
SELECT 
    A1.NOMBRE, TRUNC(MONTHS_BETWEEN(SYSDATE,A1.FECHA_NACIMIENTO)/12) AS EDAD, 
    A2.NOMBRE,TRUNC(MONTHS_BETWEEN(SYSDATE,A2.FECHA_NACIMIENTO)/12) AS EDAD,
    A1.APELLIDO1 
FROM ALUMNOS A1, ALUMNOS A2
WHERE UPPER(A1.APELLIDO1) = UPPER(A2.APELLIDO1) AND A1.DNI < A2.DNI



/*
5. Combinaciones de apellidos que se pueden obtener con los primeros apellidos de
alumnos nacidos entre los años 2000 y 2001, ambos incluidos. Para obtener el año
usar la función TO_CHAR con ‘YYYY’. Se recomienda utilizar el operador BETWEEN …
AND … para expresar el rango de valores.
*/

SELECT 
    A1.APELLIDO1, A2.APELLIDO1
FROM 
    ALUMNOS A1, ALUMNOS A2
WHERE 
    TO_CHAR(A1.FECHA_NACIMIENTO,'YYYY') BETWEEN '2000' AND '2001'
AND
    TO_CHAR(A2.FECHA_NACIMIENTO,'YYYY') BETWEEN '2000' AND '2001'
AND
    A1.DNI < A2.DNI;

/*

7. Muestre el nombre, apellidos, nombre de la asignatura y las notas obtenidas por todos
los alumnos con más de 22 años. Utilice la función DECODE para mostrar la nota como
(Matrícula de Honor, Sobresaliente, Notable, Aprobado, Suspenso o No Presentado).
Ordene por apellidos y nombre del alumno.

*/

SELECT 
    A.NOMBRE, A.APELLIDO1, ASI.NOMBRE, 
    DECODE(M.CALIFICACION, 
    'MH', 'MATRÍCULA DE HONOR',
    'SB', 'SOBRESALIENTE',
    'NT', 'NOTABLE',
    'AP', 'APROBADO',
    'SP', 'SUSPENSO',
    'NP', 'NO PRESENTADO',
    'NO CALIFICADA') AS CALIFICACION
FROM 
    ALUMNOS A 
    JOIN MATRICULAR M ON A.DNI = M.ALUMNO
    JOIN ASIGNATURAS ASI ON ASI.CODIGO = M.ASIGNATURA
WHERE 
    TRUNC(MONTHS_BETWEEN(SYSDATE, A.FECHA_NACIMIENTO) / 12) > 22
ORDER BY 
    A.APELLIDO1 ,A.APELLIDO2,A.NOMBRE;
    
/*  
10. Busque una incongruencia en la base de datos, es decir, asignaturas en las que el
número de créditos teóricos + prácticos no sea igual al número de créditos totales.
Muestre también los profesores que imparten esas asignaturas. 
*/

SELECT DISTINCT
    ASI.NOMBRE, I.PROFESOR
FROM 
    ASIGNATURAS ASI   
    LEFT JOIN IMPARTIR I ON I.ASIGNATURA=ASI.CODIGO
WHERE
    NVL(ASI.CREDITOS,0) != NVL(ASI.TEORICOS,0)+NVL(ASI.PRACTICOS,0);


------------------------------------------------------------------------------------------------
--------------------------------------LECCIÓN 7-------------------------------------------------
------------------------------------------------------------------------------------------------  

/*
1. Liste el nombre y código de las asignaturas que tienen en su mismo curso otra con más
créditos que ella
*/
SELECT 
    A1.NOMBRE, A1.CODIGO
FROM 
    ASIGNATURAS A1
WHERE 
    EXISTS (
        SELECT 
            * 
        FROM 
            ASIGNATURAS A2 
        WHERE 
            NVL(A1.CURSO,0) = NVL(A2.CURSO,0)
        AND
            NVL (A1.CREDITOS,0) < NVL(A2.CREDITOS,0)
    );
    
/*
2. Listar el nombre de las asignaturas que tienen más créditos que TODAS las asignaturas
de segundo curso
*/

SELECT NOMBRE 
FROM ASIGNATURAS
WHERE CREDITOS > 
    ALL(SELECT CREDITOS
    FROM ASIGNATURAS
    WHERE CURSO = 2);


/*
3.Calcular el número de profesores de cada departamento. Muestre el nombre del
departamento y el número de profesores.
*/

SELECT 
    D.NOMBRE, COUNT(*)
FROM
    PROFESORES P 
    JOIN DEPARTAMENTOS D ON P.DEPARTAMENTO = D.CODIGO
GROUP BY
    D.CODIGO, D.NOMBRE;


/*
4. Calcular el número de alumnos matriculados por curso (cada alumno debe contar una
sola vez por curso, aunque esté matriculado de varias asignaturas). Utilice COUNT
(DISTINCT ...).
*/

SELECT NVL(TO_CHAR(ASI.curso), 'Sin especificar') Curso, COUNT(DISTINCT M.ALUMNO) AS "NUM ALUMNOS"

FROM 
    ALUMNOS A 
    JOIN MATRICULAR M ON M.ALUMNO = A.DNI
    JOIN ASIGNATURAS ASI ON M.ASIGNATURA = ASI.CODIGO 

GROUP BY 
     ASI.CURSO;

/*
5. Calcular, por cada asignatura, qué porcentaje de sus alumnos son mujeres. Mostrar el
código de la asignatura y el porcentaje.
*/
SELECT 
    M.ASIGNATURA, ROUND(COUNT (NULLIF(A.GENERO,'MASC'))*100/COUNT(*),2) PORCENTAJE
FROM 
    ALUMNOS A 
    JOIN MATRICULAR M ON A.DNI = M.ALUMNO
GROUP BY
    M.ASIGNATURA

/*
7. Visualizar, por cada departamento, el nombre del profesor más cercano a la jubilación
(de mayor edad).
*/
SELECT D.NOMBRE, (P.NOMBRE || ' ' || P.APELLIDO1 ||  ' ' || P.APELLIDO2) NOMBRE
FROM  DEPARTAMENTOS D
    JOIN PROFESORES P ON P.DEPARTAMENTO = D.CODIGO
WHERE 
    (D.NOMBRE, P.FECHA_NACIMIENTO) IN
        
    (SELECT 
        D.NOMBRE, MIN(P.FECHA_NACIMIENTO)
    FROM 
        DEPARTAMENTOS D
         JOIN PROFESORES P ON P.DEPARTAMENTO = D.CODIGO
    GROUP BY D.NOMBRE); 
    
/*
8. Visualizar el profesor más antiguo de cada departamento.
*/
SELECT 
    D.NOMBRE, P.NOMBRE, P.APELLIDO1, P.APELLIDO2
FROM
    PROFESORES P 
    JOIN DEPARTAMENTOS D ON P.DEPARTAMENTO = D.CODIGO
WHERE  
    (P.DEPARTAMENTO,P.ANTIGUEDAD) 
IN 
    (SELECT 
        P.DEPARTAMENTO, MIN(P.ANTIGUEDAD)
    FROM 
        PROFESORES P 
    GROUP BY 
        P.DEPARTAMENTO);
        
   
/*
10. Visualizar el profesor con mayor carga de créditos. Considere la carga de créditos como
la suma de los créditos de las asignaturas que imparte dicho profesor. Nota: Tenga en
cuenta que un profesor puede impartir sólo una parte de una asignatura, por lo que se
debe utilizar los créditos de la tabla impartir.
*/
SELECT 
    P.NOMBRE, P.APELLIDO1, SUM(I.CARGA_CREDITOS) 
FROM 
    PROFESORES P 
    JOIN IMPARTIR I ON P.ID = I.PROFESOR
GROUP BY 
    P.ID, P.NOMBRE, P.APELLIDO1
HAVING 
    SUM(
        I.CARGA_CREDITOS) = (SELECT MAX(SUM(I.CARGA_CREDITOS))FROM IMPARTIR I GROUP BY I.PROFESOR);

/*
11. Muestre el listado de los profesores que imparten menos de 10 créditos en total.
Indique el código del profesor y el número de créditos que imparte.
*/


SELECT 
    PROFESOR, SUM(CARGA_CREDITOS)
 FROM 
    IMPARTIR I
 WHERE CARGA_CREDITOS IS NOT NULL
 GROUP BY PROFESOR
 HAVING SUM(CARGA_CREDITOS) < 10

/*
12. Visualizar aquellos profesores que imparten 2 o más asignaturas en el curso 22/23 con
una carga de créditos inferior a 6.5 en cada una de ellas
*/

SELECT 
    NOMBRE, APELLIDO1, APELLIDO2
FROM 
    PROFESORES 
WHERE ID IN
    (SELECT PROFESOR
    FROM IMPARTIR
    WHERE CURSO='22/23'
    AND CARGA_CREDITOS < 6.5
    GROUP BY PROFESOR
    HAVING COUNT(*) >= 2 );
    


/*
13. Dar el nombre de las asignaturas hueso. Una asignatura se dice hueso si ningún alumno
la ha superado
*/
SELECT
    ASI.NOMBRE
FROM 
    ASIGNATURAS ASI
WHERE 
    ASI.CODIGO NOT IN (SELECT M.ASIGNATURA FROM MATRICULAR M WHERE M.CALIFICACION IN ('AP', 'NT', 'SB', 'MH'));
        
/*
14. Listar alfabéticamente los profesores que están en la lista negra de los alumnos. Si un
profesor está en la lista negra de los alumnos, da clase en alguna asignatura optativa y
en ella los alumnos no se matriculan para evitarlo. Tenga en cuenta que, si hay dos
grupos de la optativa, los alumnos tienden a evitar al profesor de ese grupo, pero no a
los de los otros grupos.
*/

SELECT DISTINCT P.NOMBRE, P.APELLIDO1, P.APELLIDO2
FROM 
    PROFESORES P 
    JOIN IMPARTIR I ON P.ID = I.PROFESOR
    JOIN ASIGNATURAS ASI ON ASI.CODIGO = I.ASIGNATURA
WHERE 
    ASI.CARACTER='OP'
AND 
    (I.ASIGNATURA, I.CURSO, I.GRUPO) 
    NOT IN (
        SELECT ASIGNATURA, CURSO, GRUPO
        FROM MATRICULAR
    )
ORDER BY 2,3,1;  


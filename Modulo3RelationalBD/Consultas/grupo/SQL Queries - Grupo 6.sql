--Christian
/*
List the employees who earn a salary higher than the average. 
Display their SSN, salary, the outlet they work at along with its location, and ensure they are sorted by salary descending order.
*/

SELECT 
    E.EMPLOYEE_SSN, E. SALARY, O.OUTLET_NAME || ' se encuentra en ' || O.CITY || ', ' || O.STREET || ', ' || O.CP OUTLET
FROM 
    EMPLOYEE E 
    JOIN OUTLET O ON E.WORKS_ON_OUTLET_NAME = O.OUTLET_NAME
WHERE 
    SALARY >
    (
        SELECT 
            AVG(SUM(SALARY))
        FROM EMPLOYEE 
        GROUP BY EMPLOYEE_SSN 
    );
    
/* 
Retrieve all individuals who have left a non-negative review on Google and purchased more than one car.
A review is considered positive if the analyzed sentiment of the text is either POSITIVE or NEUTRAL, and the rating is greater than 3.
Sort their names alphabetically by last name and first name.
*/

SELECT 
    I.FNAME || ' ' || I.LNAME AS CLIENTE
FROM 
    INDIVIDUAL_CLIENT I
    JOIN GOOGLE_REVIEW G ON G.WRITTEN_BY_CLIENT_DNI = I.CLIENT_DNI
    JOIN SEMANTIC_ANALYSIS S ON G.TEXT_ID = S.TEXT_ID
WHERE
    UPPER(S.SENTIMENT) IN ('POSITIVE', 'NEUTRAL') 
AND 
    G.STAR_RATING > 3 
AND 
    I.CUSTOMER_ID IN 
    (
        SELECT CUSTOMER_ID
        FROM SALES
        GROUP BY CUSTOMER_ID
        HAVING COUNT(*) > 1
    )
ORDER BY 
    I.LNAME,
    I.FNAME;

    
    
/* 
Create a list displaying all possible pairings of managers who started managing their respective outlets in the same month of the same year.
Display their SSNs and the name of the month they started.
 */
 
 
    SELECT 
        M1.EMPLOYEE_SSN AS MANAGER_1_SSN, 
        TO_CHAR(M1.MGR_START_DATE, 'Month')  MANAGER_1_MONTH,
        
        M2.EMPLOYEE_SSN AS MANAGER_2_SSN, 
        TO_CHAR(M2.MGR_START_DATE, 'Month')  MANAGER_2_MONTH 
    FROM
        MANAGER M1, 
        MANAGER M2
    WHERE
        TO_CHAR(M1.MGR_START_DATE, 'MM-YYYY') = TO_CHAR(M2.MGR_START_DATE, 'MM-YYYY')
    AND 
        M1.EMPLOYEE_SSN < M2.EMPLOYEE_SSN;
       
/*
Display each manager along with their SSN, sales target, and the total sales made by the employees of their outlet this month.
Ensure the results are sorted by sales target in descending order.
*/


SELECT 
    M.EMPLOYEE_SSN AS MANAGER_SSN,
    M.SALES_TARGET AS SALES_TARGET,
    NVL(V.VENTAS, 0) AS TOTAL_OUTLET_SALES
FROM 
    MANAGER M
    LEFT JOIN 
    (
        SELECT 
            E.WORKS_ON_OUTLET_NAME AS OUTLET_NAME,
            COUNT(*) AS VENTAS
        FROM 
            SALES S
            JOIN EMPLOYEE E ON S.EMPLOYEE_SSN = E.EMPLOYEE_SSN
        WHERE 
            TO_CHAR(S.BUY_DATE, 'MM-YYYY') = TO_CHAR(SYSDATE, 'MM-YYYY')
        GROUP BY 
            E.WORKS_ON_OUTLET_NAME
    ) V ON M.MANAGES_OUTLET_NAME = V.OUTLET_NAME
ORDER BY 
    M.SALES_TARGET DESC;

     
    
-- NICO

-- Crear un ranking de los empleados que tengan mejor puntaje en las preguntas que esten relacionadas con atencion al cliente,
-- muestre el empleado, su salario y la cantidad de encuestas que tiene asociadas. Sólo se deben considerar las encuestas que tengan una respuesta de texto cuya longitud sea mayor a 15 caracteres

SELECT 
    E.Employee_ssn EMPLOYEE_SSN,
    E.Salary EMPLOYEE_SALARY,
    COUNT(DISTINCT SU.Survey_id) SURVEY_COUNT,
    AVG(N.Score) AVG_SCORE
FROM ANSWER_NUMERIC N
JOIN QUESTION Q ON (Q.Survey_id = N.Survey_id AND Q.Question_code = N.Question_code)
JOIN SURVEY SU ON (SU.Survey_id = Q.Survey_id)
JOIN INDIVIDUAL_CLIENT I ON (I.Client_dni = SU.Completed_by_client_dni)
JOIN CUSTOMER C ON (C.Customer_id = I.Customer_id)
JOIN SALES SA ON (SA.Customer_id = C.Customer_id)
JOIN EMPLOYEE E ON (E.Employee_ssn = SA.Employee_ssn)
WHERE
    Q.Question_text LIKE '%experience%'
    AND Su.Survey_id IN (
        SELECT DISTINCT Survey_id
        FROM ANSWER_FREE_TEXT
        WHERE LENGTH(Text) > 15
    )
GROUP BY E.Employee_ssn, E.Salary
ORDER BY E.Salary DESC;

-- Identify quantity of cars that had negative sentiment in the semantic analysis and "Brakes" topic mentioned in both Free_text questions and Google_reviews.
-- Retrieve model, brand and version, as well as in which factory they were produced.

SELECT C.Produced_factory_name, C.Model_name, C.Brand, C."Version" "VERSION", COUNT(VIN) CARS_WITH_BRAKE_COMPLAINS
FROM CAR C
WHERE C.VIN IN (
    SELECT VSU.VIN
    FROM
        (
        SELECT DISTINCT
            SA.VIN
        FROM
            SALES SA
            JOIN INDIVIDUAL_CLIENT IC ON IC.Customer_id = SA.Customer_id
            JOIN SURVEY SU ON SU.Completed_by_client_dni = IC.Client_dni
            JOIN ANSWER_FREE_TEXT FT ON FT.Survey_id = SU.Survey_id
            JOIN SEMANTIC_ANALYSIS SA ON SA.Text_id = FT.Text_id
            JOIN TOPIC T ON T.Text_id = SA.Text_id
        WHERE
            T.Topic = 'Brakes' AND SA.Sentiment = 'Negative'
    ) VSU INNER JOIN (
        SELECT DISTINCT
            SA.VIN
        FROM
            SALES SA
            JOIN INDIVIDUAL_CLIENT IC ON IC.Customer_id = SA.Customer_id
            JOIN GOOGLE_REVIEW GR ON GR.Written_by_client_dni = IC.Client_dni
            JOIN SEMANTIC_ANALYSIS SA ON SA.Text_id = GR.Text_id
            JOIN TOPIC T ON T.Text_id = SA.Text_id
        WHERE 
            T.Topic = 'Brakes' AND SA.Sentiment = 'Negative'
    ) VGR ON VGR.VIN = VSU.VIN
)
GROUP BY C.Produced_factory_name, C.Model_name, C.Brand, C."Version"

-- Show all dealers names, Outlets names & star average, whose star average is lower than the overall averge star rating for the full company

SELECT 
    O.Dealer_name,
    O.Outlet_name,
    AVG(GR.Star_Rating) AS Star_Average
FROM OUTLET O
JOIN EMPLOYEE E ON O.Dealer_name = E.Works_on_dealer_name AND O.Outlet_name = E.Works_on_outlet_name
JOIN SALES S ON E.Employee_ssn = S.Employee_ssn
JOIN INDIVIDUAL_CLIENT I ON I.Customer_id = S.Customer_id 
JOIN GOOGLE_REVIEW GR ON I.Client_dni = GR.Written_by_client_dni
GROUP BY 
    O.Dealer_name,
    O.Outlet_name
HAVING 
    AVG(GR.Star_Rating) < (
        SELECT AVG(GR1.Star_Rating)
        FROM GOOGLE_REVIEW GR1
    );

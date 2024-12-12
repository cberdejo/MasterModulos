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
    I.LNAME ASC, 
    I.FNAME ASC; 

    
    
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

     
       



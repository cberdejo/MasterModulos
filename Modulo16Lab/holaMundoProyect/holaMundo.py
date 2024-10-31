
class Hola:
    def __init__(self):
        self.list = []

    def init(self,a: int = 2) -> None:
        """
        add integer to the list
        :param a: int
        :return: None
        """
        self.list.append(a)

    def __str__(self):
        return list.__str__

hola = Hola()
hola.init(1)
hola.init()
hola.init(3)

#array
a = [1,2,3]
a[0]

#iterar
for i in range(10):
    print(a[i])

for v in a:
    print(v)

print(1 in a) #O(n)

for i,v in enumerate(a):
    print(i,v)

#set
b = set()

print(1 in b)

c = {}
c = dict()

c = {
    "hola":2,
    "mundo":"34"
}
valor = c["hola"] #O(1)
valor = c.get("hola")


#paso por valor
a = 1
b = a

a = []
b = a.copy()

#pasos por referencia
a = []
b = a
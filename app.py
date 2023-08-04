import os

for a,b,c in os.walk("F:\programme\java\jxsd"):
    for d in c:
        try:
            e = open(f"{a}/{d}","r",encoding="utf-8").read()
        
            if "ruoyi" in e.decode():
                print(a,c);
        except Exception:
            ...

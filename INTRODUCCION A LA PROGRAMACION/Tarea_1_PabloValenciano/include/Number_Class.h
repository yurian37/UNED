#ifndef NUMBER_CLASS_H
#define NUMBER_CLASS_H
#include <iostream>
#include <string>

class Number_Class
{
    public:
        std::string Odd_Even() {
            if (Number==0) return "Is Neutral";
            else if (Number%2==0) return "Is Even";
            else return "Is Odd";
        }

    protected:

    private:
        unsigned int Number{0};
};

#endif // NUMBER_CLASS_H

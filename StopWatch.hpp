#include <chrono>

struct StopWatch
{
    std::chrono::steady_clock::time_point start;
    std::chrono::steady_clock::time_point end;
    double duration;
    StopWatch() : start(std::chrono::steady_clock::now()), duration(0) {}
    void stop()
    {
        end = std::chrono::steady_clock::now();
        duration += std::chrono::duration<double>(end - start).count();
    }
    void resume()
    {
        start = std::chrono::steady_clock::now();
        end = start;
    }
};
